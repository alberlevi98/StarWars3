package com.example.alber.starwars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RestInterface  {
    @GET("starships")
    Call<StarshipResult> getStarship();

    @GET
    Call<StarshipResult> getStarshipMore(@Url String url);

    @GET
    Call<Films> getFilms(@Url String url);
}
