package com.example.user.cookassistantupdated;

/**
 * Created by user on 3/27/2018.
 */

public class Item {
    String itemId;
    String itemName;

    public Item() {
    }

    public Item(String itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
