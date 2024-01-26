package com.cassioluciano.guaranisystemtomanage.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cassioluciano.guaranisystemtomanage.Model.ClienteModel;
import com.cassioluciano.guaranisystemtomanage.R;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<ClienteModel> clientes;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ClienteModel cliente);
    }

    public ClienteAdapter(List<ClienteModel> clientes, OnItemClickListener onItemClickListener) {
        this.clientes = clientes;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        ClienteModel cliente = clientes.get(position);
        holder.bind(cliente, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public void atualizarClientes(List<ClienteModel> novosClientes) {
        clientes.clear();
        clientes.addAll(novosClientes);
        notifyDataSetChanged();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewRazaoSocial;
        private TextView textViewNomeFantasia;
        private TextView textViewCnpj;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRazaoSocial = itemView.findViewById(R.id.textViewRazaoSocial);
            textViewNomeFantasia = itemView.findViewById(R.id.textViewNomeFantasia);
            textViewCnpj = itemView.findViewById(R.id.textViewCnpj);
        }

        public void bind(ClienteModel cliente, OnItemClickListener onItemClickListener) {
            textViewRazaoSocial.setText(cliente.getRazaoSocial());
            textViewNomeFantasia.setText(cliente.getNomeFantasia());
            textViewCnpj.setText(cliente.getCnpj());

            // Configurar o clique no item
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(cliente));
        }
    }
}
