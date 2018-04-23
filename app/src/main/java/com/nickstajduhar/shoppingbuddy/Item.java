package com.nickstajduhar.shoppingbuddy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nickstajduhar on 2018-03-26.
 */

public class Item implements Parcelable{

    //properties for the package
    private int isle;
    private int id;
    //private String[] nut;
    private String side;
    private String name;
    private Double price;
    private Double ppPound;
    private String itemImg;

    DatabaseHandler db;

    //constructor
    public Item(String name, int isle, double price, String itemImg){
        this.name = name;
        this.isle = isle;
        //this.nut = nut;
        //this.side = side;
        this.price = price;
        //this.ppPound = ppPound;
        this.itemImg = itemImg;
    }


    public Item(){

    }


    public Item(int isle) {
        this.isle = isle;
    }

    public Item(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public int getIsle() {
        return isle;
    }

    public void setIsle(int isle) {
        this.isle = isle;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPpPound() {
        return ppPound;
    }

    public void setPpPound(Double ppPound) {
        this.ppPound = ppPound;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }


    public int getId() {
        return id;
    }

    public void setId(){this.id = id;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.isle);
        dest.writeDouble(this.price);
        dest.writeString(this.itemImg);

    }

    protected Item(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.isle = in.readInt();
        this.price = in.readDouble();
        this.itemImg = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
