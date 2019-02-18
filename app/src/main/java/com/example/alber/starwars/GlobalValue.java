package com.example.alber.starwars;

import android.app.Application;

public class GlobalValue extends Application {
    int pagenumber = 2;

    public int returnNumber(){
        return pagenumber;
    }
    public void setNumber(int number){
        this.pagenumber = number;
    }
}
