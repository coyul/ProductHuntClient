package ru.coyul.producthuntclient;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.coyul.producthuntclient.model.Product;

public class ProductAdapter extends BaseAdapter {

    private List<Product> mProductList;

    public ProductAdapter() {
        mProductList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Product getItem(int i) {
        return mProductList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.product_list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) view.findViewById(R.id.name);
            viewHolder.mDescription = (TextView) view.findViewById(R.id.description);
            viewHolder.mUpVotes = (TextView) view.findViewById(R.id.votes);
            viewHolder.mThumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Product product = mProductList.get(position);

        if (product != null) {
            viewHolder.mName.setText(product.getName());
            viewHolder.mDescription.setText(product.getDescription());
            viewHolder.mUpVotes.setText(String.valueOf(product.getUpVotes()));

            Picasso.with(view.getContext())
                    .load(product.getThumbnailUrl().getThumbnailUrl())
                    .into(viewHolder.mThumbnail);
        }
        return view;
    }


    public void setUpAdapter(List<Product> list) {
        mProductList.clear();
        mProductList.addAll(list);
        notifyDataSetChanged();
    }


    private static class ViewHolder {
        private ImageView mThumbnail;
        private TextView mName;
        private TextView mDescription;
        private TextView mUpVotes;
    }
}
