package com.example.peretztest2;

import com.google.gson.annotations.SerializedName;

public class Dish {
    private String image;
    private String name;
    private String description;
    private int price;
    private int id;
    @SerializedName("new")
    private boolean badgeNew;

    public Dish(String image, String name, String description, int price, int id) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    public boolean isBadgeNew() {return badgeNew;}
    public void setBadgeNew() {this.badgeNew = badgeNew;}

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
