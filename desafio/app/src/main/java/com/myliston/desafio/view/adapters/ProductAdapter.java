package com.myliston.desafio.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myliston.desafio.R;
import com.myliston.desafio.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    // Construtor e métodos do adaptador

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDescription;
        private TextView txtCode;
        private TextView txtStock;
        private TextView txtMaxPrice;
        private TextView txtMinPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicialize as views aqui
        }
    }
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }



    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.txtDescription.setText(product.getDescription());
        holder.txtCode.setText(product.getCode());
        holder.txtStock.setText(String.valueOf(product.getStock()));
        holder.txtMaxPrice.setText(String.valueOf(product.getMaxPrice()));
        holder.txtMinPrice.setText(String.valueOf(product.getMinPrice()));
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }
}
