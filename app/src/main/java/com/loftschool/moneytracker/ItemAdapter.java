package com.loftschool.moneytracker;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Класс адаптер
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewholder> { //Наследуется от адаптера в бибилиотек RecyclerView

    private List<Item> items = new ArrayList<>(); // Новый массив с items-ами

    ItemAdapter() { //Конструктор сосписком айтемов
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
    public ItemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);//сформировали список по Item.xml
        return new ItemViewholder(view); //Метод вернет новую позицию, заполненную по классу ItemViewholder
    }

    @Override
    public void onBindViewHolder(ItemViewholder holder, int position) {
       Item item = items.get(position);
       holder.bind(item);
    }


    @Override //количество элементов списка в памяти
    public int getItemCount() {

        return items.size(); //Вернуть размер массива items
    }

    static class ItemViewholder extends RecyclerView.ViewHolder { //Создали класс для зполнения одной позиции

        private TextView name; //поле с именем
        private TextView price; //поле с ценой

        ItemViewholder(View itemView) { //Конструктор
            super(itemView);

            name = itemView.findViewById(R.id.itemName); //Заполнили имя
            price = itemView.findViewById(R.id.itemPrice); //Заполнили цену
        }

        void bind(Item item){ //метод для заполнения позиций текстом и ценой
            name.setText(String.valueOf(item.getName())); //Позиция заполняется текстом
            price.setText(String.valueOf(item.getPrice())); //Ценой
        }
}

}
