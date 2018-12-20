package com.example.user.cookassistantupdated;

/**
 * Created by user on 3/27/2018.
 */

class Recipe {
    String pro;
    String time;

    public Recipe() {
    }

    Recipe(String pro, String time){
        this.pro=pro;
        this.time=time;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
