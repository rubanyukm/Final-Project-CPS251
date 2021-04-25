package com.ebookfrenzy.roomdemo.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ebookfrenzy.roomdemo.Contact;
import com.ebookfrenzy.roomdemo.ContactRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private ContactRepository repository;
    private LiveData<List<Contact>> allContacts;
    private List<Contact> alphaSort;
    private MutableLiveData<List<Contact>> searchResults;

    public MainViewModel(Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
        searchResults = repository.getSearchResults();
        alphaSort = repository.sortAZ();
    }

    public MutableLiveData<List<Contact>> getSearchResults() {
        return searchResults;
    }
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }
    public void insertContact(Contact contact) {
        repository.insertContact(contact);
    }
    public void findContact(String name) {
        repository.findContact(name);
    }
    public void deleteContact(String name) { repository.deleteContact(name);}
    public List<Contact> sortAZ() { return alphaSort;}

}