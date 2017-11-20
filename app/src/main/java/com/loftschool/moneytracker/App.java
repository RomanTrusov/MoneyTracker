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

public class App extends Application { //создали класс апп, который сущ-ет пока сущ-ет приложение в памяти

    private Api api; //создали пременную для работы с интерфейсом

    @Override
    public void onCreate() { //когда приложение открылось
        super.onCreate();

        Gson gson = new GsonBuilder() //настройка Гсон
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //способ отображения даты
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //способ отображения текстат в Гсон
                .create(); //закончить настройку



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(); //новый объект interceptor

        interceptor.setLevel(BuildConfig.DEBUG ? BODY: NONE); //задали уровень логирования //если версия дебажная то полный лог иначе никакой

        OkHttpClient client = new OkHttpClient.Builder() //настройка поведения клинта
                .addInterceptor(interceptor) //задали интресептор
                .build(); //закончили настройку

        Retrofit retrofit = new Retrofit.Builder() //преинициализация ретрофит
                .baseUrl("http://loftschoolandroid1117.getsandbox.com/") //с этого сайта берем инфу
                .addConverterFactory(GsonConverterFactory.create(gson)) //по такому конверетру
                .client(client) //клиент
                .build(); //конец билдера

        api = retrofit.create(Api.class); //используем интерфейс для работы с инетом
        }

    public Api getApi() {
        return api;
    }

}
