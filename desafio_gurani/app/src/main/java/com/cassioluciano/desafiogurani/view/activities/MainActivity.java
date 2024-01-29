package com.cassioluciano.desafiogurani.view.activities;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.PopupMenu;
import androidx.appcompat.app.AlertDialog;

import com.cassioluciano.desafiogurani.view.adapters.CustomAdapter;
import com.cassioluciano.desafiogurani.utils.DatabaseHelper;
import com.cassioluciano.desafiogurani.R;
import com.cassioluciano.desafiogurani.utils.RecyclerTouchListener;
import com.cassioluciano.desafiogurani.view.fragments.ResultFragment;
import com.cassioluciano.desafiogurani.model.Cliente;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private CustomAdapter customAdapter;

    private List<Cliente> resultList = new ArrayList<>();

    private SearchTask searchTask;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddClienteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Cliente cliente = resultList.get(position); // Modificação aqui

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("CLIENTE", cliente);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                // Ação de clique longo
                showPopupMenu(view, position);
            }
        }));
    }
    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Obtenha as informações do item na posição especificada
        Cliente selectedCliente = resultList.get(position);

        // Customize a mensagem do diálogo com as informações do item
        builder.setTitle("Confirmação")
                .setMessage("Deseja realmente excluir o seguinte item?\n\n" +
                        "Cod.: " + selectedCliente.getCodigo() +
                        ", Razão Social: " + selectedCliente.getRazaoSocial() +
                        ", Nome Fantasia: " + selectedCliente.getNomeFantasia() +
                        ", CNPJ: " + selectedCliente.getCnpj())
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Use o código do cliente para exclusão
                        String codigo = selectedCliente.getCodigo();

                        // Remova da lista
                        resultList.remove(position);
                        customAdapter.notifyDataSetChanged();

                        // Exclua do banco de dados
                        deleteItemFromDatabase(codigo);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void deleteItemFromDatabase(String codigo) {
        SQLiteDatabase db = null;
        try {
            // Abre o banco de dados para escrita
            db = dbHelper.getWritableDatabase();
            if (db != null) {
                // Execute a exclusão com base no código
                db.delete(DatabaseHelper.TABLE_CLIENTES, DatabaseHelper.COLUMN_CODIGO_CLIENTE + " = ?", new String[]{codigo});
                Log.d("MainActivity", "Item excluído do banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbHelper.closeDatabase(db);
            Log.d("MainActivity", "Closed database");
        }
    }

    private void showPopupMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.inflate(R.menu.context_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_delete) {
                    showDeleteConfirmationDialog(position);
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
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

    private class SearchTask extends AsyncTask<String, Void, List<Cliente>> {

        @Override
        protected List<Cliente> doInBackground(String... params) {
            return searchClientes(params[0]);
        }

        @Override
        protected void onPostExecute(List<Cliente> newResultList) {
            resultList.clear();
            resultList.addAll(newResultList);

            if (newResultList.isEmpty()) {
                Toast.makeText(MainActivity.this, "Nenhum resultado encontrado", Toast.LENGTH_SHORT).show();
            }

            customAdapter.notifyDataSetChanged();

            // usando ResultFragment, atualize os resultados lá
            ResultFragment resultFragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.resultsRecyclerView);
            if (resultFragment != null) {
                resultFragment.updateResults(newResultList);
            }
        }
        private List<Cliente> searchClientes(String query) {
            SQLiteDatabase db = null;
            Cursor cursor = null;
            List<Cliente> newResultList = new ArrayList<>(); // Modificação aqui

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
                        int codigoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CODIGO_CLIENTE);
                        int razaoSocialIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_RAZAO_SOCIAL);
                        int nomeFantasiaIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME_FANTASIA);
                        int cnpjIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CNPJ);

                        if (razaoSocialIndex >= 0 && nomeFantasiaIndex >= 0 && cnpjIndex >= 0) {
                            String codigo = cursor.getString(codigoIndex);
                            String razaoSocial = cursor.getString(razaoSocialIndex);
                            String nomeFantasia = cursor.getString(nomeFantasiaIndex);
                            String cnpj = cursor.getString(cnpjIndex);

                            Cliente cliente = new Cliente(codigo, razaoSocial, nomeFantasia, cnpj); // Modificação aqui
                            newResultList.add(cliente);
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

}



