package com.loftschool.moneytracker;

//Класс с информацией о каждом item
public class Item {

    public static final String TYPE_UNKNOWN = "unknown"; //Неизвестная страница
    public static final String TYPE_EXPENSE = "expence"; //первый тип для затрат
    public static final String TYPE_INCOME = "income"; //второй тип для прибыли


    public int id; //айди айтема
    public String name; //инициация строки с названием
    public int price; //инициация цены
    public String type; //тип айтема

    public Item(String name, int price, String type) { //конструктор для определения инициированных переменных  (с помощью Alt+Insert)
        this.name = name; //определение имени item
        this.price = price; //определение цены item
        this.type = type; //определение типа
    }
}