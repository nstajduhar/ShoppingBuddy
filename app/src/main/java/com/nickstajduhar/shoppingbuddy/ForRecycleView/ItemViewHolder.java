package com.nickstajduhar.shoppingbuddy.ForRecycleView;

import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nickstajduhar.shoppingbuddy.DatabaseHandler;
import com.nickstajduhar.shoppingbuddy.Item;
import com.nickstajduhar.shoppingbuddy.R;

import java.net.URI;


/**
 * Created by nickstajduhar on 2018-03-26.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder{

    //views
    private ImageView item_icon;
    private TextView item_title;
    private TextView item_price;
    private TextView item_isle;
    private ImageView item_fav;
    private ImageView item_delete;

    private RelativeLayout inventoryItem;


    public ItemViewHolder(View itemView) {
        super(itemView);
        item_icon = (ImageView) itemView.findViewById(R.id.item_icon);
        item_title = (TextView) itemView.findViewById(R.id.item_title);
        item_price = (TextView) itemView.findViewById(R.id.item_price);
        item_isle = (TextView) itemView.findViewById(R.id.item_isle);

        item_fav = (ImageView) itemView.findViewById(R.id.favorite);
        //item_side = (TextView) itemView.findViewById(R.id.item_side);


        inventoryItem = (RelativeLayout) itemView.findViewById(R.id.inventoryItem);

    }



    public ImageView getItem_icon() {
        return item_icon;
    }


    public TextView getItem_title() {
        return item_title;
    }



    public TextView getItem_price() {
        return item_price;
    }



    public TextView getItem_isle() {
        return item_isle;
    }



    public ImageView getItem_fav() {

        return item_fav;
    }


    public RelativeLayout getInventoryItem() {
        return inventoryItem;
    }



}
