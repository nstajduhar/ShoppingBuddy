package com.nickstajduhar.shoppingbuddy.ForRecycleView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nickstajduhar.shoppingbuddy.Bakery;
import com.nickstajduhar.shoppingbuddy.CreateItem;
import com.nickstajduhar.shoppingbuddy.DatabaseHandler;
import com.nickstajduhar.shoppingbuddy.Item;
import com.nickstajduhar.shoppingbuddy.ItemView;
import com.nickstajduhar.shoppingbuddy.MainActivity;
import com.nickstajduhar.shoppingbuddy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nickstajduhar on 2018-03-26.
 */

public class itemAdapter extends RecyclerView.Adapter<ItemViewHolder> {


    //array of packages going to te recyclerView
    private ArrayList<Item> list = null;

    //activity and fragmentManager
    private MainActivity activity;
    private FragmentManager fm;

    /**
     *
     * @param list the list use to populate items in the recyclerView
     * @param activity needed to create fragment transactions from this class
     */
    public itemAdapter(ArrayList<Item> list, MainActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    public itemAdapter(ArrayList<Item> allItems) {
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        // holder.getPackageIcon().setImageResource(list.get(position).getImagesId()[0]);
        Picasso.with(activity.getBaseContext()).load(list.get(position).getItemImg()).resize(50,25).centerCrop().into(holder.getItem_icon());
        DatabaseHandler db = new DatabaseHandler(null);
        holder.getItem_title().setText(list.get(position).getName());
        holder.getItem_isle().setText(list.get(position).getIsle());
        holder.getItem_price().setText(list.get(position).getPrice().toString());
        //Image in inventory
        //holder.getItem_icon().setText(list.get(position).getSide());

         /*
        * I made a setOnClickListener for the relativeLayout to simulate when an item is pressed. I made it this way
        * because we only need to press the item just once and this way makes it easy.
        *
        * It will pass the list of tripPackages and position, so that we can locate the one we are currently
        *   on and populate the fragment with the correct information
        *
        * */
        holder.getInventoryItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NICK", list.get(position).getName() + " was pressed");
                fm = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                transaction.replace(R.id.content_main, ItemView.newInstance(list, position));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
