package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Новый активити со списком трат (item-ов)
public class ItemsFragment extends Fragment { //наследуется от Fragment, так как это фрагмент

    private static final int TYPE_UNKNOWN = -1; //Неизвестная страница
    public static final int TYPE_EXPENSE = 0; //первый тип для затрат
    public static final int TYPE_INCOME = 1; //второй тип для прибыли

    private static final String KEY_TYPE = "TYPE"; // Ключ

    private int type = TYPE_EXPENSE; //новая переменная с типом

    public static ItemsFragment createItemsFragment(int type) { //создадим метод для оптимизации кода (создание фрагмента)
        ItemsFragment fragment = new ItemsFragment(); //создали новый фрагмент
        Bundle bundle = new Bundle(); //создали новый бандл
        bundle.putInt(ItemsFragment.KEY_TYPE, type); //присвоили ему интовой значене переменной тайпа на ключ тайп
        fragment.setArguments(bundle); //задали аргумент фрагменту из бандла
        return fragment; //вернули фрагмент
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//При создании вьюшки берем стиль xml
        return inflater.inflate(R.layout.fragment_items, container, false); //возвращаем вьюшку
}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) { //Когда вьюшка создана
        RecyclerView recycler = view.findViewById(R.id.recycler); //использовать RecyclerView по айди из xml файла
        recycler.setLayoutManager(new LinearLayoutManager(getContext())); // Задать LayoutManager
        recycler.setAdapter(new ItemAdapter()); //Использовать созданный адаптер

        type = getArguments().getInt(KEY_TYPE, TYPE_UNKNOWN);

        if (type == TYPE_UNKNOWN) { //Если тип равен неизвестному
            throw new IllegalStateException("Unknown Fragment Type"); //Выдаст ошибку
        }
    }
}
