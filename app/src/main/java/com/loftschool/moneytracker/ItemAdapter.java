package com.loftschool.moneytracker;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

// Класс адаптер
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> { //Наследуется от адаптера в бибилиотек RecyclerView

    private List<Item> items = new ArrayList<>(); // Новый массив с items-ами

    public ItemAdapter() {
        items.add(new Item("Молоко", 35));
        items.add(new Item("Сыр", 115));
        items.add(new Item("Колбаса", 300));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Сыр", 115));
        items.add(new Item("Колбаса", 300));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Сыр", 115));
        items.add(new Item("Колбаса", 300));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Сыр", 115));
        items.add(new Item("Колбаса", 300));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Сыр", 115));
        items.add(new Item("Колбаса", 300));
        items.add(new Item("Молоко", 35));
        items.add(new Item("Сыр", 115));
        items.add(new Item("Колбаса", 300));
    }

    @Override //выполнение метода при создании списка
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override //выполнение метода при заполнении списка
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override //количество элементов списка в памяти
    public int getItemCount() {

        return items.size(); //Вернуть размер массива items
    }


}
