package com.loftschool.moneytracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BalanceFragment extends Fragment { //Наследутся от fragment

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//При создании вьюшки берем стиль xml
        return inflater.inflate(R.layout.fragment_balance, container, false); //возвращаем вьюшку
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) { //когда аью создана

    }
}
