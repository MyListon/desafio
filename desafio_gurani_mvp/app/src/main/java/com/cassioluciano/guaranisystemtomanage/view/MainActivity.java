package com.cassioluciano.guaranisystemtomanage.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cassioluciano.guaranisystemtomanage.Model.EnderecoModel;
import com.cassioluciano.guaranisystemtomanage.R;
import com.cassioluciano.guaranisystemtomanage.Model.ClienteModel;
import com.cassioluciano.guaranisystemtomanage.presenter.ClientePresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClienteContract.View {

    private ClienteContract.Presenter presenter;
    private ClienteAdapter clienteAdapter;
    private EditText editTextTermoPesquisa;
    private RecyclerView recyclerViewClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        Log.d("MainActivity", "onCreate chamado");

        presenter = new ClientePresenter(this, this);

        // Inicializar os componentes da UI
        editTextTermoPesquisa = findViewById(R.id.editTextTermoPesquisa);
        recyclerViewClientes = findViewById(R.id.recyclerViewClientes);

        // Configurar o RecyclerView
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));
        clienteAdapter = new ClienteAdapter(new ArrayList<>(), this::mostrarDetalhesCliente);
        recyclerViewClientes.setAdapter(clienteAdapter);

        // Configurar o botão de pesquisa
        Button btnPesquisar = findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(v -> {
            Log.d("MinhaTag", "Botão Pesquisar clicado");
            String termoPesquisa = editTextTermoPesquisa.getText().toString();
            presenter.buscarClientes(termoPesquisa);
        });
    }

    // Implemente  métodos

    @Override
    public void showClientes(List<ClienteModel> clientes) {
        Log.d("MinhaTag", "Mostrando clientes: " + clientes.size()); // Adicione este log
        clienteAdapter.atualizarClientes(clientes);
    }

    @Override
    public void mostrarMensagem(String mensagem) {
        Log.d("MinhaTag", "Mensagem: " + mensagem); // Adicione este log
        // Implemente a lógica para exibir mensagens na interface do usuário
    }

    @Override
    public void showDetalhesCliente(ClienteModel cliente, List<EnderecoModel> enderecos) {
        Log.d("MinhaTag", "Detalhes do cliente: " + cliente.getCnpj()); // Adicione este log
        // Implemente a lógica para exibir os detalhes do cliente na tela
        // Este método será chamado quando o usuário clicar em um cliente na lista
    }

    @Override
    public void mostrarDetalhesCliente(ClienteModel clienteModel) {
        Log.d("MinhaTag", "Mostrar detalhes do cliente: " + clienteModel.getCpf()); // Adicione este log
    }
}

