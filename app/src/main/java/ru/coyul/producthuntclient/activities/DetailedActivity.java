package ru.coyul.producthuntclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.coyul.producthuntclient.R;
import ru.coyul.producthuntclient.model.Product;

public class DetailedActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage;
    private TextView mName;
    private TextView mDescription;
    private TextView mUpVotes;
    private Button mButton;

    private Product mProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        initViews();

        mProduct = (Product) getIntent().getSerializableExtra("Product");
        Picasso.with(getApplicationContext())
                .load(mProduct.getPictureUrl().getScreenshotUrl())
                .into(mImage);
        mName.setText(mProduct.getName());
        mDescription.setText(mProduct.getDescription());
        mUpVotes.setText(String.valueOf(mProduct.getUpVotes()));
        mButton.setOnClickListener(this);
    }

    public void initViews() {
        mImage = (ImageView) findViewById(R.id.detailed_image);
        mName = (TextView) findViewById(R.id.detailed_name);
        mDescription = (TextView) findViewById(R.id.detailed_description);
        mUpVotes = (TextView) findViewById(R.id.detailed_votes);
        mButton = (Button) findViewById(R.id.button_get_web);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(DetailedActivity.this, WebActivity.class);
        intent.putExtra("url", mProduct.getSiteUrl());
        startActivity(intent);
    }
}
