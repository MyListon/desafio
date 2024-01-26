package com.cassioluciano.desafiogurani;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private CustomAdapter customAdapter;
    private List<String> resultList;

    private SearchTask searchTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        resultList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(resultList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SearchView searchView = findViewById(R.id.searchView);
        setupSearchView(searchView);
    }
    private void setupSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    moveCursorToEnd(searchView);
                }
            }
        });
    }

    private void moveCursorToEnd(SearchView searchView) {
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        int length = searchEditText.getText().length();
        searchEditText.setSelection(length);
    }

    private void performSearch(String query) {
        if (searchTask != null && searchTask.getStatus() == AsyncTask.Status.RUNNING) {
            searchTask.cancel(true);
        }

        searchTask = new SearchTask();
        searchTask.execute(query);
    }

    private class SearchTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            return searchClientes(params[0]);
        }

        @Override
        protected void onPostExecute(List<String> newResultList) {
            resultList.clear();
            resultList.addAll(newResultList);

            if (newResultList.isEmpty()) {
                Toast.makeText(MainActivity.this, "Nenhum resultado encontrado", Toast.LENGTH_SHORT).show();
            }

            customAdapter.notifyDataSetChanged();
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
                    cursor.close();
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
