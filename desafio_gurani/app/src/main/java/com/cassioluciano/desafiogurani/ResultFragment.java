package com.cassioluciano.desafiogurani;





import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultFragment extends Fragment {

        private List<String> resultList;
        private RecyclerView resultsRecyclerView;
        private ResultAdapter adapter;

        public ResultFragment(List<String> resultList) {
            this.resultList = resultList;
        }

        public ResultFragment() {
            // Construtor padrão vazio
        }

        public static ResultFragment newInstance(List<String> resultList) {
            return new ResultFragment(resultList);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_result, container, false);

            resultsRecyclerView = view.findViewById(R.id.resultsRecyclerView);

            // Configure o RecyclerView com um LinearLayoutManager e o adapter
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
            resultsRecyclerView.setLayoutManager(layoutManager);

            adapter = new ResultAdapter(resultList);
            resultsRecyclerView.setAdapter(adapter);

            return view;
        }

        public void updateResults(List<String> updatedResults) {
            resultList.clear();
            resultList.addAll(updatedResults);
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
