package com.nickstajduhar.shoppingbuddy;

/**
 * Created by nickstajduhar on 2018-03-26.
 */

public class Item {

    //properties for the package
    private int isle;
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
}
