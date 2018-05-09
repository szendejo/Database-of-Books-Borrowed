package com.example.csci4391.databasesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener,
                                                                StudentListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.pushFragment(new FirstFragment(), false);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    void pushFragment(Fragment newFragment, boolean addToStack) {
        // Create a FragmentTransaction from FragmentManager via activity
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.fragmentContainer, newFragment);
        if (addToStack) {
            // Add the transaction to the back stack
            transaction.addToBackStack(null);
        }
        //Commit the transaction
        transaction.commit();
    }


}
