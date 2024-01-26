package com.cassioluciano.guaranisystemtomanage.view;

import com.cassioluciano.guaranisystemtomanage.Model.PrecoModel;
import com.cassioluciano.guaranisystemtomanage.Model.ProdutoModel;

import java.util.List;

public interface ProdutoContract {
    interface View {
        void showProdutos(List<ProdutoModel> produtos);
        void showDetalhesProduto(ProdutoModel produto, List<PrecoModel> precos);
        // Outros métodos necessários
    }

    interface Presenter {
        void buscarProdutosPorStatus(String status);
        void buscarDetalhesProduto(String codigoProduto);
    }
}
