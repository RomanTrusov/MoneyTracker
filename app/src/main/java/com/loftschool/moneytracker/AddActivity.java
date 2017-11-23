package com.loftschool.moneytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


//Код пернесен их MainActivity
public class AddActivity extends AppCompatActivity {

    public boolean nameEntered = false, moneyEntered = false; //Вводим две переменные для проверки наличия текста в каждом окне

    public static final String EXTRA_TYPE = "type"; //константа типа входящих данных
    public static final String RESULT_ITEM = "item"; //константа входящего итема
    public static final int RC_ADD_ITEM = 99; //
    private String type; //определим тип прежде чем с ним работать


    protected void activeButton(ImageButton add) { //Метод для определения активности кнопки
        add.setEnabled(nameEntered && moneyEntered); //Задать активность кнопки (1\0)
        if (nameEntered & moneyEntered) {
            add.setAlpha(1.0f);
        } //Яркая если активна
        else {
            add.setAlpha(0.5f);
        } //Тусклая если не активна
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add); //верстка из activity_main
        final EditText titleEdit = findViewById(R.id.name); //Активируем ввод текста для кода
        final EditText moneyEdit = findViewById(R.id.money); //Активируем ввод денег для кода
        final ImageButton addButton = findViewById(R.id.add); //Активируем кнопку для кода
        addButton.setEnabled(false); //Делаем кнопку неактивной здесь, потому что ImageButton не деактивируется через xml (только через android:clickable)



        Toolbar toolbar = findViewById(R.id.toolbar); //определили тулбар
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //показывать кнопку "назад" в тубаре
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        type = getIntent().getStringExtra(EXTRA_TYPE); //получили тип
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent(); //новый интент на получение результата
                result.putExtra(RESULT_ITEM,new Item(titleEdit.getText().toString(),Integer.parseInt(moneyEdit.getText().toString()),type)); //вставить в результат инфу об айтеме
                setResult(RESULT_OK, result);//отправили обратно результат
                finish(); //завершить активити

            }
        });

        titleEdit.addTextChangedListener(new TextWatcher() { //Отслеживание текста в поле nameEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nameEntered = !TextUtils.isEmpty(s); //nameEntered = true если что-то введено в текст
                activeButton(addButton); //Обработка метода определения активности кнопки
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        moneyEdit.addTextChangedListener(new TextWatcher() { //Отслеживание текста в поле moneyEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                moneyEntered = !TextUtils.isEmpty(s);//moneyEntered = true если что-то введено в числа
                activeButton(addButton); //Обработка метода определения активности кнопки
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

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


