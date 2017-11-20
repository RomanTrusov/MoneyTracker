package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity { //наследуется

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //верстка из activity_main

        ViewPager pager = findViewById(R.id.pages); //задали переменную pager для отображения страниц
        TabLayout tabs = findViewById(R.id.tabs); //переменная tabs для отображения вкладок

        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), getResources())); //Задаем адаптер для pager, с доступом в ресурсы
        tabs.setupWithViewPager(pager); //tabs спросит у pager сколько нужно вкладок (по количеству страниц)
    }
}