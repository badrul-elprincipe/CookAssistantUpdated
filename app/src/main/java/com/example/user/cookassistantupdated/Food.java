package com.example.user.cookassistantupdated;

/**
 * Created by user on 3/27/2018.
 */

public class Food {
    String foodID;
    String foodName;

    public Food() {
    }

    public Food(String foodID, String foodName) {
        this.foodID = foodID;
        this.foodName = foodName;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
