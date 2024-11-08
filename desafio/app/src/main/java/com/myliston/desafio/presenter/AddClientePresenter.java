package com.myliston.desafio.presenter;

import com.myliston.desafio.contract.AddClienteContract;
import com.myliston.desafio.model.Cliente;

public class AddClientePresenter implements AddClienteContract.Presenter {

    private AddClienteContract.View view;

    public AddClientePresenter(AddClienteContract.View view) {
        this.view = view;
    }

    @Override
    public void addCliente(Cliente cliente) {

        if (cliente != null) {
            view.showSuccessMessage();
        } else {
            view.showErrorMessage("Erro ao adicionar cliente");
        }
    }
}
