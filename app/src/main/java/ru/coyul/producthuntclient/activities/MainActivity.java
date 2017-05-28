package ru.coyul.producthuntclient.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.coyul.producthuntclient.ProductAdapter;
import ru.coyul.producthuntclient.ProductHuntApplication;
import ru.coyul.producthuntclient.storage.ProductHuntStorage;
import ru.coyul.producthuntclient.R;
import ru.coyul.producthuntclient.network.ProductHuntNetworkService;
import ru.coyul.producthuntclient.model.ProductResponse;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ProductAdapter mProductsAdapter;
    private ListView mProductsListView;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Spinner mSpinner;

    private int mCurrentCategory;
    private ProductHuntStorage mStorage;

    private static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductHuntApplication application = (ProductHuntApplication) getApplication();
        mStorage = application.getProductHuntStorage();

        initViews();
        mProductsAdapter = new ProductAdapter();
        mProductsListView.setAdapter(mProductsAdapter);

        setUpToolbar();

    }


    public void loadData() {
        Log.e(TAG, "loadData");
        Call<ProductResponse> call = new ProductHuntNetworkService()
                .getApi()
                .getPosts(getString(R.string.developer_token), getResources().getStringArray(R.array.category_array)[mCurrentCategory]);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response != null) {
                    Log.e(TAG, "onResponse");
                    mStorage.setLoadedList(response.body().getResults());

                    if (mStorage.getLoadedList().isEmpty()) {
                        Log.e(TAG, "onResponse empty list");
                        Toast.makeText(MainActivity.this, R.string.no_data_error, Toast.LENGTH_LONG).show();
                        mProductsListView.setVisibility(View.GONE);
                        cancelLoadingAnimation();
                    } else {
                        Log.e(TAG, "onResponse OK list");
                        mProductsAdapter.setUpAdapter(mStorage.getLoadedList());
                        mProductsListView.setVisibility(View.VISIBLE);
                        cancelLoadingAnimation();
                    }

                } else {
                    Log.e(TAG, "onResponse null response");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e(TAG, "onFailure " + t.getMessage(), t);
            }
        });

    }

    public void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mProductsListView = (ListView) findViewById(R.id.list_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    public void setUpToolbar() {

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getSupportActionBar().getThemedContext(), R.array.category_array, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int category, long id) {
        Log.e(TAG, "onItemSelected");
        mProgressBar.setVisibility(View.VISIBLE);

        if (!mStorage.isReady() || mCurrentCategory != category) {
            Log.e(TAG, "onItemSelected empty storage");
            mCurrentCategory = category;
            loadData();
        } else {
            Log.e(TAG, "onItemSelected full storage");
            mProductsAdapter.setUpAdapter(mStorage.getLoadedList());
            mProductsListView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }

        mProductsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                Log.e(TAG, "onItemClick");
                if (mStorage.isReady()) {
                    Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                    intent.putExtra("Product", mStorage.getLoadedList().get(pos));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void cancelLoadingAnimation(){
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
