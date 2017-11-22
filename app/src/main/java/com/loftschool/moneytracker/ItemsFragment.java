package com.loftschool.moneytracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loftschool.moneytracker.api.AddResult;
import com.loftschool.moneytracker.api.Api;

import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.loftschool.moneytracker.Item.TYPE_UNKNOWN;

// Новый активити со списком трат (item-ов)
public class ItemsFragment extends Fragment { //наследуется от Fragment, так как это фрагмент

    private static final int ITEMS_LOADER = 0; //айди лоадера
    private static final int ITEMS_ADD = 1; //айди добавления

    private static final String KEY_TYPE = "TYPE"; // Ключ
    private String type = TYPE_UNKNOWN; //новая переменная с типом

    private ItemAdapter adapter; // Обозначили переменную адаптер
    private Api api; //Обозначили ппеременную апи

    public static ItemsFragment createItemsFragment(String type) { //создадим метод для оптимизации кода (создание фрагмента)
        ItemsFragment fragment = new ItemsFragment(); //создали новый фрагмент
        Bundle bundle = new Bundle(); //создали новый бандл
        bundle.putString(ItemsFragment.KEY_TYPE, type); //присвоили ему интовой значене переменной тайпа на ключ тайп
        fragment.setArguments(bundle); //задали аргумент фрагменту из бандла
        return fragment; //вернули фрагмент
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) { //когда создается фрагмент
        super.onCreate(savedInstanceState);

        type = getArguments().getString(KEY_TYPE, TYPE_UNKNOWN); //можно проанализировать это не создавая вьюху

        if (type.equals(TYPE_UNKNOWN)) { //Если тип равен неизвестному
            throw new IllegalStateException(getString(R.string.unknownFragmentType)); //Выдаст ошибку
        }

        adapter = new ItemAdapter(); //создали адаптер здесь
        api = ((App) getActivity().getApplication()).getApi(); //получили api с помощью приведения

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
        recycler.setAdapter(adapter); //Использовать созданный адаптер

        FloatingActionButton fab = view.findViewById(R.id.fab_add); //определили кнопку
        fab.setOnClickListener(new View.OnClickListener() { //присвоили чекинг нажатия
            @Override
            public void onClick(View v) { //при нажатии
                Intent intent = new Intent(getActivity(),AddActivity.class); //создаем намерение перейти на AddActivity
                intent.putExtra(AddActivity.EXTRA_TYPE,type); //передае тип через ключ
                startActivityForResult(intent,AddActivity.RC_ADD_ITEM); //перейти на активити с переменной
            }
        });

        loadItems(); //метод загрузки айтомов (ниже. они могут быть разными)

    }

    private void loadItems() { //метод загрузки айтемов
        getLoaderManager().initLoader(ITEMS_LOADER, null, new LoaderManager.LoaderCallbacks<List<Item>>() { //используем лоадеры
            @SuppressLint("StaticFieldLeak") //может быть утечка памяти
            @Override
            public Loader<List<Item>> onCreateLoader(int id, Bundle args) { //на момент создания лоадера

                return new AsyncTaskLoader<List<Item>>(getContext()) { //новый метод асинктаск
                    @Override
                    public List<Item> loadInBackground() { //на другом потоке
                        try { //попробовать
                            return api.items(type).execute().body(); //вернуть айтемы
                        } catch (IOException e) { //в лучае ошибки
                            e.printStackTrace();
                            return null; //ничего не вернуть
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<List<Item>> loader, List<Item> items) { //когда завершится загрузка
                if (items == null) { //если айтемы пусты
                    showError(); //ошибка (наш метод ниже)
                } else { //иначе
                    adapter.setItems(items); //заполнить адаптер
                }

            }

            @Override
            public void onLoaderReset(Loader<List<Item>> loader) { //удаление ссылок на сброшенные данные

            } //используем лоад менеджер

        }).forceLoad(); //запустить лоадер


    }

    private void addItem(final Item item) {
        getLoaderManager().restartLoader(ITEMS_ADD, null, new LoaderManager.LoaderCallbacks<AddResult>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public Loader<AddResult> onCreateLoader(final int id, Bundle args) {
                return new AsyncTaskLoader<AddResult>(getContext()) {
                    @Override
                    public AddResult loadInBackground() {
                        try {
                            return api.add(item.name, item.price, item.type).execute().body();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<AddResult> loader, AddResult data) {

            }

            @Override
            public void onLoaderReset(Loader<AddResult> loader) {

            }
        }) ;
    }


    private void showError() { //метод для ошибки
        Toast.makeText(getContext(), R.string.errorItemsIsEmpty, Toast.LENGTH_SHORT).show(); //всплывающее окно с ошибкой
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { //получаем результат при переходе на это активити
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AddActivity.RC_ADD_ITEM && resultCode == RESULT_OK) { //если запрашиваемый код вавен имени добавленого итема и результат окей
            Item item = (Item) data.getSerializableExtra(AddActivity.RESULT_ITEM); // добавить объект
            Toast.makeText(getContext(),item.name + " стоило Вам  " + String.valueOf(item.price) + " руб.",Toast.LENGTH_LONG).show(); //отобразить на экране имя объекта
        }
    }
}
