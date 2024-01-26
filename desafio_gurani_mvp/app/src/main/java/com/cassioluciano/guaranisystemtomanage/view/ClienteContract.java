package com.cassioluciano.guaranisystemtomanage.view;

import com.cassioluciano.guaranisystemtomanage.Model.ClienteModel;
import com.cassioluciano.guaranisystemtomanage.Model.EnderecoModel;

import java.util.List;

public interface ClienteContract {
    interface View {
        void showClientes(List<ClienteModel> clientes);

        void mostrarMensagem(String mensagem);

       

        void showDetalhesCliente(ClienteModel cliente, List<EnderecoModel> enderecos);

        void mostrarDetalhesCliente(ClienteModel clienteModel);
    }

    interface Presenter {
        void buscarClientes(String termo);
        void cadastrarCliente(ClienteModel cliente);
        void atualizarCliente(ClienteModel cliente);
        void deletarCliente(String codigoCliente);
        void buscarDetalhesCliente(String codigoCliente);
    }
}

