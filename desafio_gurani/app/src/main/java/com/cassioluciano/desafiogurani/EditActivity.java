package com.cassioluciano.desafiogurani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EditActivity extends AppCompatActivity {

    private EditText editCodigo;
    private EditText editRazaoSocial;
    private EditText editNomeFantasia;
    private EditText editCnpj;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editCodigo = findViewById(R.id.edit_codigo);
        editRazaoSocial = findViewById(R.id.edit_razao_social);
        editNomeFantasia = findViewById(R.id.edit_nome_fantasia);
        editCnpj = findViewById(R.id.edit_cnpj);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        Intent intent = getIntent();
        if (intent != null) {
            Cliente cliente = (Cliente) intent.getSerializableExtra("CLIENTE");

            editCodigo.setText(cliente.getCodigo());
            editRazaoSocial.setText(cliente.getRazaoSocial());
            editNomeFantasia.setText(cliente.getNomeFantasia());
            editCnpj.setText(cliente.getCnpj());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenha os dados atualizados do cliente
                String codigo = getCodigo();
                String razaoSocial = getRazaoSocial();
                String nomeFantasia = getNomeFantasia();
                String cnpj = getCnpj();

                // Atualize o banco de dados
                updateDatabase(codigo, razaoSocial, nomeFantasia, cnpj);

                // Feche a activity
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private DatabaseHelper dbHelper;
    private CustomAdapter customAdapter;
    private List<String> resultList;
    private void updateDatabase(String codigo, String razaoSocial, String nomeFantasia, String cnpj) {
        try {
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            SQLiteDatabase db = null;

            try {
                // Abre o banco de dados para escrita
                db = dbHelper.getWritableDatabase();
                if (db != null) {
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COLUMN_RAZAO_SOCIAL, razaoSocial);
                    values.put(DatabaseHelper.COLUMN_NOME_FANTASIA, nomeFantasia);
                    values.put(DatabaseHelper.COLUMN_CNPJ, cnpj);

                    Log.d("EditActivity", "Updating database for codigo: " + codigo);
                    Log.d("EditActivity", "Valores antes da atualização: Razão Social: " + getRazaoSocial() + ", Nome Fantasia: " + getNomeFantasia() + ", CNPJ: " + getCnpj());


                    int rowsAffected = db.update(
                            DatabaseHelper.TABLE_CLIENTES,
                            values,
                            DatabaseHelper.COLUMN_CODIGO_CLIENTE + " = ?",
                            new String[]{codigo}
                    );

                    if (rowsAffected > 0) {
                        Log.d("EditActivity", "Item atualizado no banco de dados. Linhas afetadas: " + rowsAffected);
                    } else {
                        Log.e("EditActivity", "Falha ao atualizar o item no banco de dados. Nenhuma linha afetada.");
                    }

                }


            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("EditActivity", "Erro ao abrir o banco de dados para escrita.");
            } finally {
                if (db != null) {
                    dbHelper.closeDatabase(db);
                    Log.d("EditActivity", "Closed database**********");
                } else {
                    Log.e("EditActivity", "Database is null************");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("EditActivity", "Erro ao criar a instância do DatabaseHelper.");
        }
    }





    public String getCodigo() {
        return editCodigo.getText().toString();
    }

    public String getRazaoSocial() {
        return editRazaoSocial.getText().toString();
    }

    public String getNomeFantasia() {
        return editNomeFantasia.getText().toString();
    }

    public String getCnpj() {
        return editCnpj.getText().toString();
    }
}

