package com.nickstajduhar.shoppingbuddy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ItemView extends Fragment {





    //keys for the bundle
    public static final String NAME = "name";
    public static final String ISLE = "isle";
    //public static final String nut = "nut";
    public static final String SIDE = "SIDE";
    public static final String PRICE = "price";
    public static final String PPPound = "ppPound";
    public static final String IMAGES_ID = "images";



    private String name;
    private String isle;
    //private String[] nut;
    private String side;
    private String price;
    private String ppPound;
    private String itemImg;


    public ItemView() {

    }


    /**
     * Receiving the array list with the position selected
     * to access all the properties from the tripPackage.
     * Then, adding them to a bundle to make use of them later in the properties
     * declared in this fragment.
     *
     * @param list ArrayList of tripPackages.
     * @param position to know which item that was pressed in the recyclerView.
     * @return A new instance of fragment TripViewFragment.
     */
    public static ItemView newInstance(ArrayList<Item> list, int position) {
        ItemView fragment = new ItemView();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, list.get(position).getName());
        bundle.putInt(ISLE, list.get(position).getIsle());
        //bundle.putStringArray(nut, list.get(position).getnut());
        bundle.putString(SIDE, list.get(position).getSide());
        bundle.putDouble(PRICE, list.get(position).getPrice());
        bundle.putDouble(PPPound, list.get(position).getPpPound());
        bundle.putString(IMAGES_ID, list.get(position).getItemImg());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //populating properties from the bundle received
            name = getArguments().getString(NAME);
            isle = getArguments().getString(ISLE);
            //nut = getArguments().getStringArray(NUT);
            side = getArguments().getString(SIDE);
            price = getArguments().getString(PRICE);
            itemImg = getArguments().getString(IMAGES_ID);
            Log.d("NICK", name + " was pressed and I received it in itemViewFragment");

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflating the view
        View view = inflater.inflate(R.layout.fragment_item_view, container, false);

        return view;
    }








    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
