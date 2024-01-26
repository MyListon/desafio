package com.cassioluciano.desafiogurani;



import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private CustomPagerAdapter pagerAdapter;
    private List<String> resultList;

    private SearchTask searchTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        resultList = new ArrayList<>();

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), resultList);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Cancela tarefas de pesquisa pendentes antes de iniciar uma nova
                if (searchTask != null && searchTask.getStatus() == AsyncTask.Status.RUNNING) {
                    searchTask.cancel(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString().trim();
                searchTask = new SearchTask();
                searchTask.execute(query);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString().trim();
                searchTask = new SearchTask();
                searchTask.execute(query);
            }
        });
    }

    private class SearchTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            return searchClientes(params[0]);
        }


        @Override
        protected void onPostExecute(List<String> newResultList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (newResultList.isEmpty()) {
                        // Mostrar mensagem de Toast se a lista estiver vazia
                        Toast.makeText(MainActivity.this, "Nenhum resultado encontrado", Toast.LENGTH_SHORT).show();
                        // Limpar a lista
                        resultList.clear();
                        // Notificar o adapter após limpar a lista
                        pagerAdapter.notifyDataSetChanged();
                    } else {
                        resultList.clear();
                        resultList.addAll(newResultList);
                        pagerAdapter.notifyDataSetChanged();
                    }
                }
            });
        }


        private List<String> searchClientes(String query) {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            List<String> newResultList = new ArrayList<>();

            try {
                db = dbHelper.openDatabaseForRead();
                Log.d("MainActivity", "Opened database for read");

                String[] columns = {DatabaseHelper.COLUMN_CODIGO_CLIENTE, DatabaseHelper.COLUMN_RAZAO_SOCIAL, DatabaseHelper.COLUMN_NOME_FANTASIA, DatabaseHelper.COLUMN_CNPJ};
                String selection = DatabaseHelper.COLUMN_RAZAO_SOCIAL + " LIKE ? OR " +
                        DatabaseHelper.COLUMN_NOME_FANTASIA + " LIKE ? OR " +
                        DatabaseHelper.COLUMN_CNPJ + " LIKE ?";
                String[] selectionArgs = {"%" + query + "%", "%" + query + "%", "%" + query + "%"};

                cursor = db.query(DatabaseHelper.TABLE_CLIENTES, columns, selection, selectionArgs, null, null, null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int razaoSocialIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_RAZAO_SOCIAL);
                        int nomeFantasiaIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME_FANTASIA);
                        int cnpjIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CNPJ);

                        if (razaoSocialIndex >= 0 && nomeFantasiaIndex >= 0 && cnpjIndex >= 0) {
                            String razaoSocial = cursor.getString(razaoSocialIndex);
                            String nomeFantasia = cursor.getString(nomeFantasiaIndex);
                            String cnpj = cursor.getString(cnpjIndex);

                            Log.d("MainActivity", "Razão Social: " + razaoSocial + ", Nome Fantasia: " + nomeFantasia + ", CNPJ: " + cnpj);

                            newResultList.add("Razão Social: " + razaoSocial + "\nNome Fantasia: " + nomeFantasia + "\nCNPJ: " + cnpj);
                        } else {
                            Log.e("MainActivity", "Índices de coluna inválidos.");
                        }
                    }
                    cursor.close(); // Fechar o cursor aqui para evitar vazamento
                } else {
                    Log.e("MainActivity", "Cursor nulo.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbHelper.closeDatabase(db);
                Log.d("MainActivity", "Closed database");
            }

            return newResultList;
        }


        private void closeCursor(Cursor cursor) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

//        @Override
//        protected void onDestroy() {
//            super.onDestroy();
//            // Garanta que o AsyncTask seja cancelado ao destruir a atividade
//            if (searchTask != null && searchTask.getStatus() == AsyncTask.Status.RUNNING) {
//                searchTask.cancel(true);
//            }
//        }
    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        CustomPagerAdapter(FragmentManager fm, List<String> resultList) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

            fragments.add(new ResultFragment(resultList));
            fragmentTitles.add("Resultados");
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }

}
//Cliente 1 RS