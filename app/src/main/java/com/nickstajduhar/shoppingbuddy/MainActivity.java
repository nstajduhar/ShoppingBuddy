package com.nickstajduhar.shoppingbuddy;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class MainActivity extends AppCompatActivity
                                implements account.OnFragmentInteractionListener,
                                            card.OnFragmentInteractionListener,
                                            inventory.OnFragmentInteractionListener,
                                            search.OnFragmentInteractionListener,
                                            CreateItem.OnFragmentInteractionListener,
                                            adminPage.OnFragmentInteractionListener,
                                            UpdateItem.OnFragmentInteractionListener,
                                            BrowseFragment.OnFragmentInteractionListener,
                                            DeleteFragment.OnFragmentInteractionListener,
                                            SettingsFragment.OnFragmentInteractionListener,
                                            MoreFragment.OnFragmentInteractionListener{

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
                    transaction.replace(R.id.content_main, new BrowseFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_lists:
                    transaction.replace(R.id.content_main, new inventory());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_more:
                    transaction.replace(R.id.content_main, new MoreFragment());
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

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("yv6poEeigtLQln0u4ReTzMOsX", "mc7ur8qs0VSun6cLSrnASXHEJFhSVEMPR2G1Ppuxitq1aT43yK"))
                .debug(true)
                .build();
        Twitter.initialize(config);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content_main, new search());
            transaction.commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
