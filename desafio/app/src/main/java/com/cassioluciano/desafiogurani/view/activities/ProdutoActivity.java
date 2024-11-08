package com.cassioluciano.desafiogurani.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cassioluciano.desafiogurani.R;
import com.cassioluciano.desafiogurani.model.Product;
import com.cassioluciano.desafiogurani.view.adapters.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        recyclerView = findViewById(R.id.recyclerView);

        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<Product> productList = getListaDeProdutos();

            adapter = new ProductAdapter(productList);
            recyclerView.setAdapter(adapter);
        }
    }


    private List<Product> getListaDeProdutos() {
        List<Product> productList = new ArrayList<>();

        productList.add(new Product("001", "Produto 1", 10, 20.0, 15.0));
        productList.add(new Product("002", "Produto 2", 15, 25.0, 18.0));

        return productList;
    }
}
