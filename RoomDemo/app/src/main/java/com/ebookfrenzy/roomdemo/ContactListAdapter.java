package com.ebookfrenzy.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.ebookfrenzy.roomdemo.ui.main.MainFragment;
import com.ebookfrenzy.roomdemo.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private MainViewModel mViewModel;
    private final int productItemLayout;
    private List<Contact> contactList;
    MainFragment mf = new MainFragment();
    public String na;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView Name = holder.name;
        TextView Phone = holder.phone;
        ImageView Trash = holder.trash;
        Name.setText(contactList.get(listPosition).getName());
        na = Name.getText().toString();
        Phone.setText(contactList.get(listPosition).getPhone());

        Trash.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                mf.deleteName();
            }
        });
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public String getText() {
        return na;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;
        ImageView trash;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameView);
            phone = itemView.findViewById(R.id.phoneView);
            trash = itemView.findViewById(R.id.trashBin);
        }
    }
}
