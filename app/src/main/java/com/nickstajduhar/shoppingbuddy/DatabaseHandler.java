package com.nickstajduhar.shoppingbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by nickstajduhar on 2018-03-29.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Keep track of the database version
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * Create the name of the database
     */
    public static final String DATABASE_NAME = "shoopingbuddy";

    /**
     * Create the names of all the tables
     */
    public static final String TABLE_ITEMS = "items";
    public static final String TABLE_FAV_ITEMS = "favorites";


    //public static final String TABLE_ITEMIMAGE = "image_item";

    /**
     * Create column names
     *
     */
    public static final String COLUMN_ID = "id";

    /**
     * Bakery, Deli, Produce table column names
     */
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PPPound = "ppPound";
    public static final String COLUMN_ISLE = "isle";
    public static final String COLUMN_ITEMIMG = "itemImg";



    /**
     * Create statements for our tables
     */

    public static final String CREATE_ITEMS_TABLE = "CREATE TABLE " +
            TABLE_ITEMS + "(" + COLUMN_NAME + " TEXT," + COLUMN_ISLE + " INT," + COLUMN_PRICE + " FLOAT," +  COLUMN_ITEMIMG + " TEXT)";

    public static final String CREATE_FAV_ITEMS_TABLE = "CREATE TABLE " +
            TABLE_FAV_ITEMS + "(" + COLUMN_NAME + " TEXT," + COLUMN_ISLE + " INT," + COLUMN_PRICE + " FLOAT," +  COLUMN_ITEMIMG + " TEXT)";



    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ITEMS_TABLE);
        db.execSQL(CREATE_FAV_ITEMS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV_ITEMS);

    }

    /**
     * CRUD OPERATION FOR THE DATABASE AND TABLES
     * Create
     * Read
     * Update
     * Delete
     */

    /**
     * CREATE OPERATIONS
     */

    public void addItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_ISLE, item.getIsle());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_ITEMIMG, item.getItemImg());
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void addFavItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_ISLE, item.getIsle());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_ITEMIMG, item.getItemImg());
        db.insert(TABLE_FAV_ITEMS, null, values);
        db.close();
    }



    /**
     * READ OPERATIONS
     */

    public ArrayList<Item> getSearchItems(String searchResult){
        ArrayList<Item> itemSearchList = new ArrayList<Item>();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE (name LIKE '%"+searchResult+"%') OR isle LIKE '%"+searchResult+"%'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemSearchList.add(new Item(
                        //name
                        cursor.getString(0),
                        //isle
                        cursor.getInt(1),
                        //price
                        cursor.getDouble(2),
                        //image
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        db.close();
        return itemSearchList;
    }

    public ArrayList<Item> getAllItems(){
        ArrayList<Item> itemList = new ArrayList<Item>();
        String query = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemList.add(new Item(
                        //name
                        cursor.getString(0),
                        //isle
                        cursor.getInt(1),
                        //price
                        cursor.getDouble(2),
                        //image
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        db.close();
        return itemList;
    }

    public ArrayList<Item> getAllFavItems(){
        ArrayList<Item> itemList = new ArrayList<Item>();
        String query = "SELECT * FROM " + TABLE_FAV_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemList.add(new Item(
                        //name
                        cursor.getString(0),
                        //isle
                        cursor.getInt(1),
                        //price
                        cursor.getDouble(2),
                        //image
                        cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        db.close();
        return itemList;
    }



    public ArrayList<Item> getAllItemsName(){
        ArrayList<Item> itemList = new ArrayList<Item>();
        String query = "SELECT name FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemList.add(new Item(cursor.getString(0)));
            } while (cursor.moveToNext());
        }


        db.close();
        return itemList;
    }

    public ArrayList<Item> getAllIFavtemsName(){
        ArrayList<Item> itemList = new ArrayList<Item>();
        String query = "SELECT name FROM " + TABLE_FAV_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemList.add(new Item(cursor.getString(0)));
            } while (cursor.moveToNext());
        }


        db.close();
        return itemList;
    }


    /**
     * UPDATE OPERATIONS
     */



    public int updateItems(Item item, String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_ISLE, item.getIsle());
        values.put(COLUMN_PRICE, item.getPrice());
        values.put(COLUMN_ITEMIMG, item.getItemImg());
        return db.update(TABLE_ITEMS, values, COLUMN_NAME + "= ?",
                new String[]{String.valueOf(name)});
    }


    /**
     * DELETE OPERATIONS
     */

    public void deleteItems(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS, COLUMN_NAME + " = ?",
                new String[]{String.valueOf(name)});
        db.close();
    }

    public void deleteFavItems(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAV_ITEMS, COLUMN_NAME + " = ?",
                new String[]{String.valueOf(name)});
        db.close();
    }


}