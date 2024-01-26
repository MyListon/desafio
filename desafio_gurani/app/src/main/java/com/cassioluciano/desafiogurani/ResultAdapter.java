package com.cassioluciano.desafiogurani;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<String> resultList;

    public ResultAdapter(List<String> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        String result = resultList.get(position);
        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    static class ResultViewHolder extends RecyclerView.ViewHolder {

        private TextView resultTextView;

        ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            resultTextView = itemView.findViewById(R.id.resultTextView);
        }

        void bind(String result) {
            resultTextView.setText(result);
        }
    }
}

