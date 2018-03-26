package com.nickstajduhar.shoppingbuddy;

/**
 * Created by nickstajduhar on 2018-03-26.
 */

public class Item {

    //properties for the package
    private String title;
    private String isle;
    //private String[] nut;
    private String side;
    private String price;
    private int[] imagesId;

    //constructor
    public Item(String title, String isle, String side, String price,
                        int[] imagesId){
        this.title = title;
        this.isle = isle;
        //this.nut = nut;
        this.side = side;
        this.price = price;
        this.imagesId = imagesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsle() {
        return isle;
    }

    public void setIsle(String isle) {
        this.isle = isle;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int[] getImagesId() {
        return imagesId;
    }

    public void setImagesId(int[] imagesId) {
        this.imagesId = imagesId;
    }
}
