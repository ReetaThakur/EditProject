package com.xyz.recyclerview;

public class Menu {
    private String itemName;
    private double itemPrice;
    private String restaurantName;

    public Menu(String itemName, double itemPrice, String restaurantName) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.restaurantName = restaurantName;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
}
