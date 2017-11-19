package com.loftschool.moneytracker;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
        items.add(new Item("Молоко", 85));
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
    public ItemViewholder onCreateViewHolder(ViewGroup parent, int viewType) { //Когда создается вьюхолдер
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);//сформировали список по Item.xml
        return new ItemViewholder(view); //Метод вернет новую позицию, заполненную по классу ItemViewholder
    }

    @Override
    public void onBindViewHolder(ItemViewholder holder, int position) {  //Когда заполняется вьюхолдер
       Item item = items.get(position); //Задать позицию айтема
       holder.bind(item); //Метод bind
    }


    @Override //количество элементов списка в памяти
    public int getItemCount() { //Количество позиций

        return items.size(); //Вернуть размер массива items
    }

    static class ItemViewholder extends RecyclerView.ViewHolder { //Создали класс для зполнения одной позиции
        //private String newPrice;
        private TextView name; //поле с именем
        private TextView price; //поле с ценой

        ItemViewholder(View itemView) { //Конструктор
            super(itemView);

            name = itemView.findViewById(R.id.itemName); //Заполнили имя
            price = itemView.findViewById(R.id.itemPrice); //Заполнили цену
        }

        void bind(Item item) { //метод для заполнения позиций текстом и ценой
            name.setText(String.valueOf(item.getName())); //Позиция заполняется текстом
            //newPrice = String.valueOf(item.getPrice()) + " \u20BD";
            Spannable Price = new SpannableString("\u20BD " + String.valueOf(item.getPrice())); //переменная Price с форматированным текстом
            Price.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //Задать форматирование текста
            price.setText(Price); //Задать текст
        }
}

}
