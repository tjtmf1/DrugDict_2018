package com.example.yangj.drugdict_2018;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends Fragment {

    DrugSearchListAdapter adapter;
    ListView listView;
    public SearchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        listView = (ListView)view.findViewById(R.id.searchList);
        return view;
    }

    public void setListView(ArrayList<com.example.force.infodb.ProductInfo> list){
        adapter = new DrugSearchListAdapter(getActivity(), R.layout.drug_list_row, list);
        listView.setAdapter(adapter);
    }
}
