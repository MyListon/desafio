package com.cassioluciano.guaranisystemtomanage.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import com.cassioluciano.guaranisystemtomanage.Model.ClienteModel;
import com.cassioluciano.guaranisystemtomanage.Model.EnderecoModel;
import com.cassioluciano.guaranisystemtomanage.data.ClienteDAO;
import com.cassioluciano.guaranisystemtomanage.data.EnderecoDAO;
import com.cassioluciano.guaranisystemtomanage.view.ClienteContract;

public class ClientePresenter implements ClienteContract.Presenter {
    private ClienteContract.View view;
    private ClienteDAO clienteDAO;
    private EnderecoDAO enderecoDAO;

    public ClientePresenter(ClienteContract.View view, Context context) {
        this.view = view;
        this.clienteDAO = new ClienteDAO(context);
        this.enderecoDAO = new EnderecoDAO(context);
        Log.d("ClientePresenter", "Construtor chamado");
    }


    @Override
    public void buscarClientes(String termo) {
        Log.d("ClientePresenter", "Buscar clientes: " + termo);
        List<ClienteModel> clientesEncontrados = clienteDAO.buscarClientes(termo);
        if (clientesEncontrados != null && !clientesEncontrados.isEmpty()) {
            view.showClientes(clientesEncontrados);
        } else {
            view.mostrarMensagem("Nenhum cliente encontrado");
        }
    }



    @Override
    public void cadastrarCliente(ClienteModel cliente) {
        // Implementar lógica para cadastrar cliente no banco de dados
    }

    @Override
    public void atualizarCliente(ClienteModel cliente) {
        // Implementar lógica para atualizar cliente no banco de dados
    }

    @Override
    public void deletarCliente(String codigoCliente) {
        // Implementar lógica para deletar cliente no banco de dados
    }

    @Override
    public void buscarDetalhesCliente(String codigoCliente) {
        // Implementar lógica para buscar detalhes do cliente no banco de dados
        // Chamar métodos da view para exibir os detalhes
    }


}
