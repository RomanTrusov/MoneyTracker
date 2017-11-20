package com.loftschool.moneytracker.api;

import com.loftschool.moneytracker.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Создали интерфейс


public interface Api {



    @GET("items") //способ ГЕТ название метода items
    Call<List<Item>> items(@Query("type") String type);

}
