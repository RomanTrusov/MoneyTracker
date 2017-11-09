package com.loftschool.moneytracker;

//Класс с информацией о каждом item
public class Item {


    private String name; //инициация строки с названием
    private int price; //инициация цены

    public Item(String name, int price) { //конструктор для определения инициированных переменных  (с помощью Alt+Insert)
        this.name = name; //определение имени item
        this.price = price; //определение цены item
    }

    public String getName() {  //Геттеры и сеттеры для двух переменных (с помощью  Alt+Insert)
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
