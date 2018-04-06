package com.nickstajduhar.shoppingbuddy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nickstajduhar on 2018-04-06.
 */

public class Bakery {
    private int id;
    private String name;
    private Double price;
    private Double ppPound;
    private String itemImg;

    public Bakery(int i, double aDouble, double cursorDouble, String string){

    }

    public Bakery(String name, Double price, Double ppPound, String itemImg) {
        this.name = name;
        this.price = price;
        this.ppPound = ppPound;
        this.itemImg = itemImg;
    }

    public Bakery(int id, String name, Double price, Double ppPound, String itemImg) {
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

   // @Override
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

    protected Bakery(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readDouble();
        this.ppPound = in.readDouble();
        this.itemImg = in.readString();
    }

    public static final Parcelable.Creator<Bakery> CREATOR = new Parcelable.Creator<Bakery>() {
        @Override
        public Bakery createFromParcel(Parcel source) {
            return new Bakery(source);
        }

        @Override
        public Bakery[] newArray(int size) {
            return new Bakery[size];
        }
    };
}

