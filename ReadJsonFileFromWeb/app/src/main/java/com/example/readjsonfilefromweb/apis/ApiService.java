package com.example.readjsonfilefromweb.apis;

import com.example.readjsonfilefromweb.models.Fruit;
import com.example.readjsonfilefromweb.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    // url: https://www.w3schools.com/js/customers_mysql.php
    // https://filesamples.com/samples/code/json/sample1.json
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://filesamples.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
    @GET("/samples/code/json/sample1.json")
    public Call<Fruit> convertObjectSingle();


    // https://jsonplaceholder.typicode.com/users
    ApiService apiServiceList = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);
    @GET("users")
    public Call<List<User>> convertObjectList();
}
