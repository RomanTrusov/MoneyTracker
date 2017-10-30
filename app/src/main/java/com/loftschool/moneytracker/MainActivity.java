package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public boolean nameEntered = false, moneyEntered = false; //Вводим две переменные для проверки наличия текста в каждом окне

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText titleEdit = (EditText) findViewById(R.id.name); //Активируем ввод текста для кода
        final EditText moneyEdit = (EditText) findViewById(R.id.money); //Активируем ввод денег для кода
        final ImageButton addButton = (ImageButton) findViewById(R.id.add); //Активируем кнопку для кода
        addButton.setEnabled(false); //Делаем кнопку неактивной здесь, потому что ImageButton не деактивируется через xml (только через android:clickable)


        titleEdit.addTextChangedListener(new TextWatcher() { //Отслеживание текста в поле nameEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count) {
                nameEntered = !TextUtils.isEmpty(s); //Если во время ввода текста в строке что-то есть, то nameEntered = true
                addButton.setEnabled(moneyEntered && nameEntered); //кнопка активна, если обе переменные true
                if (moneyEntered&&nameEntered) {addButton.setBackgroundColor(getResources().getColor(R.color.colorActiveButton));} //Цвет кнопки яркий, когда активна
                else {addButton.setBackgroundColor(getResources().getColor(R.color.colorInactiveButton));} //Тусклый, когда не активна
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        moneyEdit.addTextChangedListener(new TextWatcher() { //Отслеживание текста в поле moneyEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                moneyEntered = !TextUtils.isEmpty(s); //Если во время ввода текста в строке что-то есть, то moneyEntered = true
                addButton.setEnabled(moneyEntered&&nameEntered); //кнопка активна, если обе переменные true
                if (moneyEntered&&nameEntered) {addButton.setBackgroundColor(getResources().getColor(R.color.colorActiveButton));} //ЦВет кнопки яркий когда активна
                else {addButton.setBackgroundColor(getResources().getColor(R.color.colorInactiveButton));} //Тусклый, когда не активна
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


    }

    @Override
    protected void onStart() {super.onStart();}

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
