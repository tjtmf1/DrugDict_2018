package com.example.yangj.drugdict_2018;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends Fragment {

    DrugSearchListAdapter adapter;
    ArrayList<com.example.force.infodb.ProductInfo> list;
    public SearchListFragment() {
        // Required empty public constructor
    }
    static SearchListFragment getInstance(ArrayList<com.example.force.infodb.ProductInfo> list){
        SearchListFragment fragment = new SearchListFragment();
        fragment.list = list;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        return view;
    }

    public void setListView(){
        adapter = new DrugSearchListAdapter(getContext(), R.layout.drug_list_row, list);
        ListView listView = (ListView)getView().findViewById(R.id.searchList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.force.infodb.ProductInfo p = list.get(position);
                ((SearchDrugActivity)getActivity()).DrugDetail(p);
            }
        });
    }

    public void setList(ArrayList<com.example.force.infodb.ProductInfo> list){
        this.list = list;
        setListView();
    }


}
