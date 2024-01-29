package com.cassioluciano.desafiogurani.contract;

import com.cassioluciano.desafiogurani.model.Cliente;

public interface AddClienteContract {

    interface View {
        void showSuccessMessage();
        void showErrorMessage(String message);
    }

    interface Presenter {
        void addCliente(Cliente cliente);
    }
}
