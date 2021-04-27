package com.ebookfrenzy.roomdemo.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.widget.EditText;
import android.widget.TextView;

import com.ebookfrenzy.roomdemo.Contact;
import com.ebookfrenzy.roomdemo.MainActivity;
import com.ebookfrenzy.roomdemo.ContactListAdapter;

import android.os.Bundle;

import android.widget.Button;

import androidx.lifecycle.Observer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ebookfrenzy.roomdemo.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ContactListAdapter adapter;
    private TextView productId;
    private EditText contactName;
    private EditText contactPhone;
    private MainActivity ma = new MainActivity();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //productId = getView().findViewById(R.id.productID);
        contactName = getView().findViewById(R.id.contactName);
        contactPhone = getView().findViewById(R.id.contactPhone);
        //listenerSetup();
        observerSetup();
        recyclerSetup();
    }

     private void listenerSetup() {
        Button deleteButton = getView().findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteContact(contactName.getText().toString());
                clearFields();
            }
        });
    }

    public void sortContactsDesc(){
        Collections.sort(adapter.getContactList(), new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o2.getName().toLowerCase().compareTo(o1.getName().toLowerCase());
            }
        });
        adapter.notifyDataSetChanged();
        clearFields();
    }

    public void sortContactsAsc(){
        Collections.sort(adapter.getContactList(), new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
        adapter.notifyDataSetChanged();
        clearFields();
    }

    public String getPhone() {
        String phone = contactPhone.getText().toString();
        return phone;
    }

    public String getName() {
        String name = contactName.getText().toString();
        return name;
    }


    public void findName() {
       // String name = "%";
        mViewModel.findContact(getName());
        clearFields();
    }

    public void deleteName() {
        mViewModel.deleteContact(contactName.getText().toString());
        clearFields();
    }

    public void addContact() {
        String name = contactName.getText().toString();
        String phone = contactPhone.getText().toString();
        Contact contact = new Contact(name, phone.trim());
        mViewModel.insertContact(contact);
        clearFields();
    }

    private void observerSetup() {

        mViewModel.getAllContacts().observe(getViewLifecycleOwner(),
                new Observer<List<Contact>>() {
                    @Override
                    public void onChanged(@Nullable final List<Contact> contacts) {
                        adapter.setContactList(contacts);
                    }
                });

        mViewModel.getSearchResults().observe(getViewLifecycleOwner(),
                new Observer<List<Contact>>() {
                    @Override
                    public void onChanged(@Nullable final List<Contact> contacts) {

                        if (contacts.size() > 0) {
                            //productId.setText(String.format(Locale.US, "%d", products.get(0).getId()));
                            contactName.setText(contacts.get(0).getName());
                            //productQuantity.setText(String.format(Locale.US, "%d", products.get(0).getQuantity()));
                            contactPhone.setText(contacts.get(0).getPhone());
                        } //else {
                            //productId.setText("No Match");
                       // }
                    }
                });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;
        adapter = new ContactListAdapter(R.layout.contact_list_item);
        recyclerView = getView().findViewById(R.id.product_recycler);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void clearFields() {
        //productId.setText("");
        contactName.setText("");
        contactPhone.setText("");
    }

}