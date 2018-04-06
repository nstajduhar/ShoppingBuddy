package com.nickstajduhar.shoppingbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String TABLE_BAKERY = "bakery";
    public static final String TABLE_DELI = "deli";
    public static final String TABLE_PRODUCE = "produce";
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
    public static final String COLUMN_ITEMIMG = "itemImg";

    /**
     * Picture table column names
     */
    //public static final String COLUMN_RESOURCE = "resource";

    /**
     * ITEMIMAGE table column names
     */
    //public static final String COLUMN_PICTURE = "picture";


    /**
     * Create statements for our tables
     */

    public static final String CREATE_BAKERY_TABLE = "CREATE TABLE " +
            TABLE_BAKERY + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT," + COLUMN_PRICE + " FLOAT,"
            + COLUMN_PPPound + " FLOAT," + COLUMN_ITEMIMG + " TEXT)";

    public static final String CREATE_DELI_TABLE = "CREATE TABLE " +
            TABLE_DELI + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT," + COLUMN_PRICE + " FLOAT,"
            + COLUMN_PPPound + " FLOAT," + COLUMN_ITEMIMG + " TEXT)";

    public static final String CREATE_PRODUCE_TABLE = "CREATE TABLE " +
            TABLE_PRODUCE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT," + COLUMN_PRICE + " FLOAT,"
            + COLUMN_PPPound + " FLOAT," + COLUMN_ITEMIMG + " TEXT)";

//    public static final String CREATE_IMAGE_TRIP_TABLE = "CREATE TABLE " +
//            TABLE_IMAGETRIP + "(" + COLUMN_LOCATION + " INTEGER REFERENCES " +
//            TABLE_LOCATIONS + "(" + COLUMN_ID + ")," + COLUMN_PICTURE +
//            " INTEGER REFERENCES " + TABLE_PICTURES + "(" + COLUMN_ID + "))";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BAKERY_TABLE);
        db.execSQL(CREATE_DELI_TABLE);
        db.execSQL(CREATE_PRODUCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGETRIP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BAKERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCE);
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

    public void addBakery(Bakery bakery){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bakery.getName());
        values.put(COLUMN_PRICE, bakery.getPrice());
        values.put(COLUMN_PPPound, bakery.getPpPound());
        values.put(COLUMN_ITEMIMG, bakery.getItemImg());
        db.insert(TABLE_BAKERY, null, values);
        db.close();
    }

    public void addDeli(Deli deli){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, deli.getName());
        values.put(COLUMN_PRICE, deli.getPrice());
        values.put(COLUMN_PPPound, deli.getPpPound());
        values.put(COLUMN_ITEMIMG, deli.getItemImg());
        db.insert(TABLE_DELI, null, values);
        db.close();
    }

    public void addProduce(Produce produce){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, produce.getName());
        values.put(COLUMN_PRICE, produce.getPrice());
        values.put(COLUMN_PPPound, produce.getPpPound());
        values.put(COLUMN_ITEMIMG, produce.getItemImg());
        db.insert(TABLE_PRODUCE, null, values);
        db.close();
    }

    /**
     * READ OPERATIONS
     */

    public Bakery getBakery(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Bakery bakery = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_BAKERY,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PRICE, COLUMN_PPPound, COLUMN_ITEMIMG},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            bakery = new Bakery(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getDouble(1),
                    cursor.getDouble(2),
                    cursor.getString(3));
        }
        db.close();
        return bakery;
    }

    public ArrayList<Bakery> getAllBakery(){
        ArrayList<Bakery> itemList = new ArrayList<Bakery>();
        String query = "SELECT * FROM " + TABLE_BAKERY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                itemList.add(new Bakery(Integer.parseInt(
                        cursor.getString(0)),
                        cursor.getDouble(1),
                        cursor.getDouble(2),
                        cursor.getString(3));
            } while (cursor.moveToNext());
        }

        db.close();
        return itemList;
    }

    public Deli getDeli(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Deli deli = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_DELI,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PRICE, COLUMN_PPPound, COLUMN_ITEMIMG},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            deli = new Deli(Integer.parseInt(
                    cursor.getString(0)),
                    cursor.getDouble(1),
                    cursor.getDouble(2),
                    cursor.getString(3));
        }
        db.close();
        return deli;
    }

    public ArrayList<Picture> getAllPictures(){
        ArrayList<Picture> pictureList = new ArrayList<Picture>();
        String query = "SELECT * FROM " + TABLE_PICTURES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                pictureList.add(new Picture(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        db.close();
        return pictureList;
    }

    public Trip getTrip(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Trip trip = null;
        //table name, String Array of column names, query, String array of values that will
        // be inserted into the query
        Cursor cursor = db.query(TABLE_TRIPS,
                new String[]{COLUMN_ID, COLUMN_DATE, COLUMN_LOCATION},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            trip = new Trip(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)));
        }
        db.close();
        return trip;
    }

    public ArrayList<Trip> getAllTrips(){
        ArrayList<Trip> tripList = new ArrayList<Trip>();
        String query = "SELECT * FROM " + TABLE_TRIPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                tripList.add(new Trip(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2))));
            } while (cursor.moveToNext());
        }
        db.close();
        return tripList;
    }

    /**
     * UPDATE OPERATIONS
     * skip for now
     */
    public int updateLocation(Location location){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, location.getName());
        values.put(COLUMN_DESCRIPTION, location.getDescription());
        values.put(COLUMN_COORD, location.getLocation());
        return db.update(TABLE_LOCATIONS, values, COLUMN_ID + "= ?",
                new String[]{String.valueOf(location.getId())});
    }




    /**
     * DELETE OPERATIONS
     */

    public void deleteLocation(int location){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(location)});
        db.close();
    }
    public void deletePicture(int picture){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PICTURES, COLUMN_ID + " = ?",
                new String[]{String.valueOf(picture)});
        db.close();
    }
    public void deleteTrip(int trip){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIPS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(trip)});
        db.close();
    }

}