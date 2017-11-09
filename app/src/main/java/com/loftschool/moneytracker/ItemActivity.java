package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
// Новый активити со списком трат (item-ов)
public class ItemActivity extends AppCompatActivity { //наследуется от AppCompatActivity для совместимости

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item); //Использовать лэйаут из activity_item.xml

        RecyclerView recycler = findViewById(R.id.recycler); //использовать RecyclerView по айди из xml файла
        recycler.setLayoutManager(new LinearLayoutManager(this)); // Задать LayoutManager
    }
}
