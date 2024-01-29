package com.cassioluciano.desafiogurani.view.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cassioluciano.desafiogurani.R;
import com.cassioluciano.desafiogurani.model.Cliente;
import com.cassioluciano.desafiogurani.presenter.AddClientePresenter;
import com.cassioluciano.desafiogurani.view.AddClienteView;

public class AddClienteActivity extends AppCompatActivity implements AddClienteView {

    private AddClientePresenter presenter;

    private EditText etRazaoSocial;
    private EditText etNomeFantasia;
    private EditText etCnpj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        presenter = new AddClientePresenter(this);

        etRazaoSocial = findViewById(R.id.etRazaoSocial);
        etNomeFantasia = findViewById(R.id.etNomeFantasia);
        etCnpj = findViewById(R.id.etCnpj);

        Button btnAddCliente = findViewById(R.id.btnAddCliente);
        btnAddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar objeto Cliente com dados da interface
                Cliente cliente = new Cliente(null, etRazaoSocial.getText().toString(),
                        etNomeFantasia.getText().toString(), etCnpj.getText().toString());

                // Chamar método do presenter para adicionar cliente
                presenter.addCliente(cliente);
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish(); // Fecha a atividade atual e volta para a atividade anterior
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, "Cliente adicionado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
