package com.loftschool.moneytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.loftschool.moneytracker.api.Api;

import java.io.IOException;
import java.util.List;

import static com.loftschool.moneytracker.Item.TYPE_EXPENSE;
import static com.loftschool.moneytracker.Item.TYPE_UNKNOWN;

// Новый активити со списком трат (item-ов)
public class ItemsFragment extends Fragment { //наследуется от Fragment, так как это фрагмент


    private static final int ITEMS_LOADER = 0; //айди лоадера

    private static final String KEY_TYPE = "TYPE"; // Ключ
    private String type = TYPE_EXPENSE; //новая переменная с типом

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
            throw new IllegalStateException("Unknown Fragment Type"); //Выдаст ошибку
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
            public void onLoaderReset(Loader<List<Item>> loader) { //когда перезапускаем лоадер

            } //используем лоад менеджер

        }).forceLoad(); //запустить лоадер


    }
    private void showError() { //метод для ошибки
        Toast.makeText(getContext(), "Произошла ошибка. Айтемы пусты.",Toast.LENGTH_SHORT).show(); //всплывающее окно с ошибкой
    }
}

// //!!ВТОРОЙ МЕТОД, с утечками!!
//    private void loadItems () { //метод загрузки айтемов
//
//        new AsyncTask<Void,Void,List<Item>>() {
//
//            @Override
//            protected void onPreExecute() { //на главном потоке до работы в бэке
//                super.onPreExecute();
//            }
//
//            @Override
//            protected List<Item> doInBackground(Void... voids) { //метод вызовется на другом потоке
//                try {
//                    List<Item> items = api.items(type).execute().body(); //загрузить айтемы
//                    return items; //вернуть айтемы
//                } catch (IOException e) {
//                    e.printStackTrace(); //при ошибке
//                    return null; //ничего не вернуть
//                }
//            }
//
//            @Override
//            protected void onPostExecute(List<Item> items) { //на главном потоке после работы в бэке
//                super.onPostExecute(items);
//                adapter.setItems(items); //дать адаптеру айтемы
//            }
//        }.execute();
//
//    }
// КОНЕЦ ВТОРОГО МЕТОДА


    //!!Закомментили старый способ!!
//    private void loadItems() { //метод подгрузки айтмов
//
//        new LoadItemsTask(new Handler(Looper.getMainLooper()){
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case ITEMS_LOADED: {
//                        //noinspection unchecked
//                        adapter.setItems((List<Item>) msg.obj);
//                        break;
//                    }
//                    case ITEMS_ERROR: {
//                        showError((String) msg.obj);
//                        break;
//                    }
//                }
//            }
//        }).start(); //метод ниже с другим потоком
//    }
//
//    private static final int ITEMS_LOADED = 0; //флаги для нэндлера
//    private static final int ITEMS_ERROR = 1;
//
//    private class LoadItemsTask implements Runnable { //класс подгрузки айтемов
//
//        private Thread thread; //объявили новый поток
//        private Handler handler; //поле для хэндлера
//
//        public LoadItemsTask(Handler handler) { //метод для подгрузки айтемов
//            thread = new Thread(this); //новый поток
//            this.handler = handler; //созранили в переменную
//        }
//
//        public void start() {
//            thread.start(); //метод запуска потока
//        }
//
//        @Override
//        public void run() { //что обработает поток
//            try {
//                List<Item> items = api.items(type).execute().body(); //получим список айтемов
//                handler.obtainMessage(ITEMS_LOADED, items).sendToTarget(); //передача сообщения
//            } catch (Exception e) {
//                e.printStackTrace(); //ошибка
//                handler.obtainMessage(ITEMS_ERROR, e.getMessage()).sendToTarget(); //передача сообщения
//            }
//
//        }
//
//
//    }
//    !!Конец старого способа
