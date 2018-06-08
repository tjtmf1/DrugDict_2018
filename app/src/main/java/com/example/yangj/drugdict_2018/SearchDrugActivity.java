package com.example.yangj.drugdict_2018;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.force.infodb.ProductInfo;

import java.util.ArrayList;

public class SearchDrugActivity extends AppCompatActivity {
    ListView searchByName;
    EditText searchEdit;
    ArrayList<com.example.force.infodb.ProductInfo> productInfos;
    ArrayList<ProductInfo> searchProducts;
    DrugListAdapter adapter;
    SearchListFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_drug);
        init();
    }

    public void init(){
        //////////////////////////탭바 설정 시작
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.content1);
        tab1.setIndicator("이름으로 찾기");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.content2);
        tab2.setIndicator("모양으로 찾기");
        tabHost.addTab(tab2);

        searchByName = (ListView)findViewById(R.id.searchList);
        searchEdit = (EditText)findViewById(R.id.searchEdit);
        productInfos = new ArrayList<>();
        // 데이터 가져오기
        productInfos.add(new ProductInfo("name1", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name2", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name3", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name4", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name5", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name11", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name12", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name13", "img", "shape", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name41", "img", "shape", "1", "1", "1", "1"));

        fragment = (SearchListFragment) getFragmentManager().findFragmentById(R.id.searchByName);
        fragment.setListView(productInfos);
        }

    public void SearchByName(View view) {
        String search = searchEdit.getText().toString();
        if(searchProducts != null)
            searchProducts.clear();
        else
            searchProducts = new ArrayList<>();
        for(ProductInfo p : productInfos){
            if(p.getmName().contains(search)){
                searchProducts.add(p);
            }
        }
        fragment.setListView(searchProducts);
    }
}

