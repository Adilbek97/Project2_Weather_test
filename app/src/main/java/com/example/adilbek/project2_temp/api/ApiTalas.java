package com.example.adilbek.project2_temp.api;

import com.example.adilbek.project2_temp.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiTalas {
    String BASE_URL = "http://api.apixu.com/";
    @GET("v1/current.json?key=775f00d9a2754b1e9c0130256182106&q=Talas%20Kyrgyzstan")
    Call<Example> getData();
}
