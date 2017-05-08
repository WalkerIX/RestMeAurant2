package com.org.restMeaurant.data;

public class Restaurant {
    private String name;
    private String address;
    private String cuisine;
    private int priceLevel;
    private SatisfactionLevel satisfactionLevel;

    public Restaurant(){
        name = "";
        address = "";
        cuisine = "";
        priceLevel = 1;
        satisfactionLevel = SatisfactionLevel.NEUTRAL;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public SatisfactionLevel getSatisfactionLevel() {
        return satisfactionLevel;
    }

}
