package com.myliston.desafio.contract;

import com.myliston.desafio.model.Cliente;

public interface AddClienteContract {

    interface View {
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter {
        void addCliente(Cliente cliente);
    }
}
