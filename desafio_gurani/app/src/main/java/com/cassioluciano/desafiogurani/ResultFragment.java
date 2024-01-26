package com.cassioluciano.desafiogurani;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class ResultFragment extends Fragment {

    private List<String> resultList;
    private ListView resultsListView;
    private ArrayAdapter<String> adapter;

    public ResultFragment(List<String> resultList) {
        this.resultList = resultList;
    }

    public static ResultFragment newInstance(List<String> resultList) {
        return new ResultFragment(resultList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        resultsListView = view.findViewById(R.id.resultsListView);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, resultList);
        resultsListView.setAdapter(adapter);

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
