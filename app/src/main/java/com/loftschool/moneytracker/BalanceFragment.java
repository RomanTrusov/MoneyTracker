package com.loftschool.moneytracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BalanceFragment extends Fragment { //Наследутся от fragment

    private static final String TYPE_UNKNOWN = "unknown"; //в случае неизветсного типа
    public static final String TYPE_BALANCE = "balance"; //нумерация типов страниц

    private static final String KEY_TYPE = "TYPE"; //ключ

    private String type = "balance"; //тип равен текущему


    public static BalanceFragment createBalanceFragment(String type) { //создадим метод для оптимизации кода (создание фрагмента)
        BalanceFragment fragment = new BalanceFragment(); //создали новый фрагмент
        Bundle bundle = new Bundle(); //создали новый бандл
        bundle.putString(BalanceFragment.KEY_TYPE, type); //присвоили ему интовой значене переменной тайпа на ключ тайп
        fragment.setArguments(bundle); //задали аргумент фрагменту из бандла
        return fragment; //вернули фрагмент
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//При создании вьюшки берем стиль xml
        return inflater.inflate(R.layout.fragment_balance, container, false); //возвращаем вьюшку
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) { //когда аью создана
        type = getArguments().getString(KEY_TYPE, TYPE_UNKNOWN);

        if (type.equals(TYPE_UNKNOWN)) { //Если тип равен неизвестному
            throw new IllegalStateException("Unknown Fragment Type"); //Выдаст ошибку
        }
    }
}
