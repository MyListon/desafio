package com.cassioluciano.desafiogurani.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cassioluciano.desafiogurani.R;
import com.cassioluciano.desafiogurani.model.Cliente;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Cliente> resultList;

    // Construtor
    public CustomAdapter(List<Cliente> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    // onBindViewHolder method in CustomAdapter
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente cliente = resultList.get(position);

        // Verificar se as TextViews não são nulas
        if (holder.txtCodigo != null) {
            holder.txtCodigo.setText("Código: " + cliente.getCodigo());
        }
        if (holder.txtRazaoSocial != null) {
            holder.txtRazaoSocial.setText("Razão Social: " + cliente.getRazaoSocial());
        }
        if (holder.txtNomeFantasia != null) {
            holder.txtNomeFantasia.setText("Nome Fantasia: " + cliente.getNomeFantasia());
        }
        if (holder.txtCnpj != null) {
            holder.txtCnpj.setText("CNPJ: " + cliente.getCnpj());
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCodigo;
        public TextView txtRazaoSocial;
        public TextView txtNomeFantasia;
        public TextView txtCnpj;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            txtRazaoSocial = itemView.findViewById(R.id.txtRazaoSocial);
            txtNomeFantasia = itemView.findViewById(R.id.txtNomeFantasia);
            txtCnpj = itemView.findViewById(R.id.txtCnpj);
        }
    }
}
