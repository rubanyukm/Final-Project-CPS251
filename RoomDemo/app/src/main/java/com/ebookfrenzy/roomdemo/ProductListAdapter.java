package com.ebookfrenzy.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final int productItemLayout;
    private List<Product> productList;
    String a = "";

    public ProductListAdapter(int layoutId) {
        productItemLayout = layoutId;
    }

    public void setProductList(List<Product> products) {
        productList = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView Name = holder.name;
        TextView Phone = holder.phone;
        Name.setText(productList.get(listPosition).getName());
        Phone.setText(productList.get(listPosition).getQuantity());

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameView);
            phone = itemView.findViewById(R.id.phoneView);
        }
    }
}
