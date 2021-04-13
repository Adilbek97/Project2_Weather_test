package com.example.adilbek.project2_temp.api;

import com.example.adilbek.project2_temp.models.Example;
import com.example.adilbek.project2_temp.models.newModels.Example2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiTemp {
    String BASE_URL = "http://api.weatherstack.com/";
//    @GET("v1/current.json?key=775f00d9a2754b1e9c0130256182106")
    @GET("current?access_key=91fa333b4debdacc1e4dafe46b50a723")
    Call<Example2>getData(@Query("query") String query);
}
