package com.nickstajduhar.shoppingbuddy;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity
                                implements account.OnFragmentInteractionListener,
                                            card.OnFragmentInteractionListener,
                                            grocery.OnFragmentInteractionListener,
                                            inventory.OnFragmentInteractionListener,
                                            search.OnFragmentInteractionListener,
                                            CreateItem.OnFragmentInteractionListener,
                                            adminLoginFragment.OnFragmentInteractionListener{

    FragmentManager fm;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction transaction = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_search:
                    transaction.replace(R.id.content_main, new search());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_browse:
                    //Testing DB change back to browse after testing
                    transaction.replace(R.id.content_main, new CreateItem());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_lists:
                    transaction.replace(R.id.content_main, new inventory());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_card:
                    transaction.replace(R.id.content_main, new card());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_account:
                    transaction.replace(R.id.content_main, new account());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fm = getSupportFragmentManager();




        setContentView(R.layout.activity_main);


        if(savedInstanceState == null){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content_main, new search());
            transaction.commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public interface FragmentChangeListener
    {
        public void replaceFragment(Fragment fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
