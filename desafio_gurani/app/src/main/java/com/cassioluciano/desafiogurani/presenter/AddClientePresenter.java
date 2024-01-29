package com.cassioluciano.desafiogurani.presenter;

import com.cassioluciano.desafiogurani.contract.AddClienteContract;
import com.cassioluciano.desafiogurani.model.Cliente;

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
