package com.example.alber.starwars;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StarshipResult {


    @Expose
    @SerializedName("next")
    public String next;
    @Expose
    @SerializedName("results")
    public List<Starship> results;
}
