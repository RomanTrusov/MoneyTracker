package com.loftschool.moneytracker;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter { //наследуется

    private final static int PAGE_EXPENSES = 0; //переменные для определения позициис страницы
    private final static int PAGE_INCOMES = 1;
    private final static int PAGE_BALANCE = 2;


    private String[] titles; //Массив для перечня названий вкладок

    public MainPagerAdapter(FragmentManager fm, Resources resources) { //конструктор для подключения fragmentManager с доступом в ресурсы
        super(fm);

        titles = resources.getStringArray(R.array.tabs_titles); //взять массив из xml
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) { //свитч на позицию
            case PAGE_EXPENSES: { //если позиция равна 0
                return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_EXPENSE); //вернули фрагмент через метод
            }
            case PAGE_INCOMES: { //если позиция равна 1
                return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_INCOME); //вернули фрагмент через метод
            }
            case PAGE_BALANCE: //если позиция равна 3
                return null;
            default: //все остальные случаи
                return null;

        }
    }

    @Override
    public int getCount() { //количество позиций
        return 2; //из массива
    }

    @Override
    public CharSequence getPageTitle(int position) { //определение названия страницы
        return titles[position];
    }
}
