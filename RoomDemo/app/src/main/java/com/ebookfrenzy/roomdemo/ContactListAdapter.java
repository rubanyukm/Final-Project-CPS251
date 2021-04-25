package com.ebookfrenzy.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private final int productItemLayout;
    private List<Contact> contactList;
    String a = "";

    public ContactListAdapter(int layoutId) {
        productItemLayout = layoutId;
    }

    public void setContactList(List<Contact> contacts) {
        contactList = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactList == null ? 0 : contactList.size();
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
        Name.setText(contactList.get(listPosition).getName());
        Phone.setText(contactList.get(listPosition).getPhone());

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
