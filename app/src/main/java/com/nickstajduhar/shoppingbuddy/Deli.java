package com.nickstajduhar.shoppingbuddy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nickstajduhar on 2018-04-06.
 */

public class Deli {
    private int id;
    private String name;
    private Double price;
    private Double ppPound;
    private String itemImg;

    public Deli(){

    }

    public Deli(String name, Double price, Double ppPound, String itemImg) {
        this.name = name;
        this.price = price;
        this.ppPound = ppPound;
        this.itemImg = itemImg;
    }

    public Deli(int id, String name, Double price, Double ppPound, String itemImg) {
        this.name = name;
        this.price = price;
        this.ppPound = ppPound;
        this.itemImg = itemImg;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String toString(){
        return this.name;
    }

    //@Override
    public int describeContents() {
        return 0;
    }

    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeDouble(this.price);
        dest.writeDouble(this.ppPound);
        dest.writeString(this.itemImg);
    }

    protected Deli(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readDouble();
        this.ppPound = in.readDouble();
        this.itemImg = in.readString();
    }

    public static final Parcelable.Creator<Deli> CREATOR = new Parcelable.Creator<Deli>() {
        @Override
        public Deli createFromParcel(Parcel source) {
            return new Deli(source);
        }

        @Override
        public Deli[] newArray(int size) {
            return new Deli[size];
        }
    };
}

