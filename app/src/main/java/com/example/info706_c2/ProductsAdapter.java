package com.example.info706_c2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private ArrayList<ProductsList> mProductsList;

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
        }
    }

    public ProductsAdapter(ArrayList<ProductsList> productsList) {
        mProductsList = productsList;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list, parent, false);
        ProductsViewHolder evh = new ProductsViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        ProductsList currentItem = mProductsList.get(position);

        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
    }

    @Override
    public int getItemCount() {
        return mProductsList.size();
    }
}
