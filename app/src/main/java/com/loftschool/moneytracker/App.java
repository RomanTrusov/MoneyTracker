package com.loftschool.moneytracker;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loftschool.moneytracker.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

//ЭТОТ КЛАСС ЖИВЕТ ВСЕГДА

public class App extends Application { //создали класс апп, который сущ-ет пока сущ-ет приложение в памяти

    private Api api; //создали пременную для работы с интерфейсом

    @Override
    public void onCreate() { //когда приложение открылось
        super.onCreate();

        Gson gson = new GsonBuilder() //настройка Гсон
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //способ отображения даты
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //способ отображения текстат в Гсон: нижний с подчеркиванием
                .create(); //закончить настройку

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(); //новый объект interceptor - "выхватытватель"

        //интересный метод условий ниже////если версия дебажная то полный лог иначе никакой
        interceptor.setLevel(BuildConfig.DEBUG ? BODY: NONE); //задали уровень логирования интресептора

        OkHttpClient client = new OkHttpClient.Builder() //настройка поведения клиента
                .addInterceptor(interceptor) //задали интерсептор
                .build(); //закончили настройку

        Retrofit retrofit = new Retrofit.Builder() //настройка ретрофит
                .baseUrl("http://loftschoolandroid1117.getsandbox.com/") //с этого сайта берем инфу
                .addConverterFactory(GsonConverterFactory.create(gson)) //по такому конверетру (из библиотеки)
                .client(client) //клиент (настроен выше)
                .build(); //конец билдера

        api = retrofit.create(Api.class); //используем интерфейс для работы с инетом
        }

    public Api getApi() {
        return api;
    } //геттер для переменной api

}
