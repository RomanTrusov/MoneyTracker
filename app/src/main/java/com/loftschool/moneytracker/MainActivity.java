package com.loftschool.moneytracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    protected void activeButton(boolean b1, boolean b2, ImageButton add) { //Метод для определения активности кнопки
        add.setEnabled(b1 && b2); //Задать активность кнопки (1\0)
        if (b1&&b2) {add.setBackgroundColor(getResources().getColor(R.color.colorActiveButton));} //Яркая если активна
        else {add.setBackgroundColor(getResources().getColor(R.color.colorInactiveButton));} //Тусклая если не активна
    }

    public boolean nameEntered = false, moneyEntered = false; //Вводим две переменные для проверки наличия текста в каждом окне

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //верстка из activity_main
        final EditText titleEdit = (EditText) findViewById(R.id.name); //Активируем ввод текста для кода
        final EditText moneyEdit = (EditText) findViewById(R.id.money); //Активируем ввод денег для кода
        final ImageButton addButton = (ImageButton) findViewById(R.id.add); //Активируем кнопку для кода
        addButton.setEnabled(false); //Делаем кнопку неактивной здесь, потому что ImageButton не деактивируется через xml (только через android:clickable)



        titleEdit.addTextChangedListener(new TextWatcher() { //Отслеживание текста в поле nameEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count) {
                nameEntered = !TextUtils.isEmpty(s); //nameEntered = true если что-то введено в текст
                activeButton(nameEntered, moneyEntered, addButton); //Обработка метода определения активности кнопки
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        moneyEdit.addTextChangedListener(new TextWatcher() { //Отслеживание текста в поле moneyEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                moneyEntered = !TextUtils.isEmpty(s);//moneyEntered = true если что-то введено в числа
                activeButton(nameEntered, moneyEntered, addButton); //Обработка метода определения активности кнопки
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
