package com.loftschool.moneytracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BalanceFragment extends Fragment { //Наследутся от fragment

    private static final int TYPE_UNKNOWN = -1; //в случае неизветсного типа
    public static final int TYPE_BALANCE = 2; //нумерация типов страниц

    private static final String KEY_TYPE = "TYPE"; //ключ

    private int type = TYPE_BALANCE; //тип равен текущему


    public static BalanceFragment createBalanceFragment(int type) { //создадим метод для оптимизации кода (создание фрагмента)
        BalanceFragment fragment = new BalanceFragment(); //создали новый фрагмент
        Bundle bundle = new Bundle(); //создали новый бандл
        bundle.putInt(BalanceFragment.KEY_TYPE, type); //присвоили ему интовой значене переменной тайпа на ключ тайп
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
        type = getArguments().getInt(KEY_TYPE, TYPE_UNKNOWN);

        if (type == TYPE_UNKNOWN) { //Если тип равен неизвестному
            throw new IllegalStateException("Unknown Fragment Type"); //Выдаст ошибку
        }
    }
}
