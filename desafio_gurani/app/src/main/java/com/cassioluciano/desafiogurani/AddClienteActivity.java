package com.cassioluciano.desafiogurani;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClienteActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private EditText etCodigoCliente, etRazaoSocial, etNomeFantasia, etCnpj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        dbHelper = new DatabaseHelper(this);
       // etCodigoCliente = findViewById(R.id.etCodigoCliente);
        etRazaoSocial = findViewById(R.id.etRazaoSocial);
        etNomeFantasia = findViewById(R.id.etNomeFantasia);
        etCnpj = findViewById(R.id.etCnpj);

        Button btnAddCliente = findViewById(R.id.btnAddCliente);
        btnAddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String razaoSocial = etRazaoSocial.getText().toString();
                String nomeFantasia = etNomeFantasia.getText().toString();
                String cnpj = etCnpj.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.insertCliente(db,  razaoSocial, nomeFantasia, cnpj);
                dbHelper.closeDatabase(db);

                Toast.makeText(AddClienteActivity.this, "Cliente adicionado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });

        // Adiciona o botão de voltar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


