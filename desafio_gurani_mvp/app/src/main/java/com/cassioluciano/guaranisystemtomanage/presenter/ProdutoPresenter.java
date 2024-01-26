package com.cassioluciano.guaranisystemtomanage.presenter;

import android.content.Context;

import com.cassioluciano.guaranisystemtomanage.data.PrecoDAO;
import com.cassioluciano.guaranisystemtomanage.data.ProdutoDAO;
import com.cassioluciano.guaranisystemtomanage.view.ProdutoContract;

public class ProdutoPresenter implements ProdutoContract.Presenter {
    private ProdutoContract.View view;
    private ProdutoDAO produtoDAO;
    private PrecoDAO precoDAO;

    public ProdutoPresenter(ProdutoContract.View view, Context context) {
        this.view = view;
        this.produtoDAO = new ProdutoDAO(context);
        this.precoDAO = new PrecoDAO(context);
    }

    @Override
    public void buscarProdutosPorStatus(String status) {
        // Implementar lógica para buscar produtos por status no banco de dados
        // Chamar métodos da view para exibir os resultados
    }

    @Override
    public void buscarDetalhesProduto(String codigoProduto) {
        // Implementar lógica para buscar detalhes do produto no banco de dados
        // Chamar métodos da view para exibir os detalhes e preços
    }
}
