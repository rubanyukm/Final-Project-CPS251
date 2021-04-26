package com.ebookfrenzy.roomdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebookfrenzy.roomdemo.ui.main.MainFragment;
import com.ebookfrenzy.roomdemo.ui.main.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainFragment mf;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance()).commitNow();
        }
        if (savedInstanceState == null) {
            mf = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    // It's almost always a good idea to use .replace instead of .add so that
                    // you never accidentally layer multiple Fragments on top of each other
                    // unless of course that's your intention
                    .replace(R.id.container, mf, "main_activity")
                    .commit();
        } else {
 /*The Activity is being re-created so we don't
 need to instantiate the Fragment or add it,
 but if we need a reference to it, we can use the tag we passed to .replace*/
            mf = (MainFragment)
                    getSupportFragmentManager().findFragmentByTag("main_activity");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_contact:
                if(mf.getName().equals("")) {
                    showToast("Enter a contact name");
                }
                else if(mf.getPhone().equals("")) {
                    showToast("Enter a contact phone number");
                }
                else {
                    mf.addContact();
                }
                return true;

            case R.id.show_all:
                return true;

            case R.id.find_contact:
                if(mf.getName().equals("")) {
                    showToast("Enter a contact name");
                }
                else {
                    mf.findName();
                }
                return true;

            case R.id.sort_az:
                mf.sortContactAZ();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String txt) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText(txt);
        toastImage.setImageResource(R.drawable.ic_toasticon);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}