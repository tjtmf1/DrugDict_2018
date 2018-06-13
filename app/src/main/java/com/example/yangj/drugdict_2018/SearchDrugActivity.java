package com.example.yangj.drugdict_2018;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;


import java.util.ArrayList;

public class SearchDrugActivity extends AppCompatActivity implements SearchByShapeFragment.callListListener {
    ListView searchByName;
    EditText searchEdit;
    ArrayList<ProductInfo> productInfos;
    ArrayList<ProductInfo> searchProducts;
    ArrayList<ProductInfo> bucketDrug;
    SearchListFragment fragment;
    SearchListFragment bucketFragment;
    TabHost tabHost;
    DrugSearchListAdapter bucket;
    Handler handler;
    ExcelData excelData;
    LoadingDialog dialog;

    static final int ADD_BUCKET = 100;
    static final int PUT_IN_BUCKET = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_drug);
        init();
    }

    @SuppressLint("HandlerLeak")
    public void init() {

        setupToolBar();

        //////////////////////////탭바 설정 시작
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.content1);
        tab1.setIndicator("이름으로 찾기");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.content2);
        tab2.setIndicator("모양으로 찾기");
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setContent(R.id.content3);
        tab3.setIndicator("약바구니");
        tabHost.addTab(tab3);

        searchByName = (ListView) findViewById(R.id.searchList);
        searchEdit = (EditText) findViewById(R.id.searchEdit);
        productInfos = new ArrayList<>();
        searchProducts = new ArrayList<>();
        // 데이터 가져오기
        /*productInfos.add(new ProductInfo("name1", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name2", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name3", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name4", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name5", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name11", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name12", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name13", "img", "shape", "1", "1", "1", "1", "1", "1"));
        productInfos.add(new ProductInfo("name41", "img", "shape", "1", "1", "1", "1", "1", "1"));*/

        fragment = (SearchListFragment) getFragmentManager().findFragmentById(R.id.searchByName);
        fragment.setList(productInfos);

        bucketFragment = (SearchListFragment) getFragmentManager().findFragmentById(R.id.drugBucket);
        bucketDrug = new ArrayList<>();
        bucket = new DrugSearchListAdapter(getApplicationContext(), R.layout.taking_drug_layout, bucketDrug);
        ListView bucketList = (ListView) bucketFragment.getView().findViewById(R.id.searchList);
        bucketList.setAdapter(bucket);

        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(bucketList,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    bucket.remove(bucket.getItem(position));
                                }
                                bucket.notifyDataSetChanged();
                            }
                        });
        bucketList.setOnTouchListener(touchListener);
        bucketList.setOnScrollListener(touchListener.makeScrollListener());

        FragmentManager fragmentManager = getFragmentManager();
        // 새로 생성 해주는 부분
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SearchByShapeFragment fragment1 = new SearchByShapeFragment();
        fragmentTransaction.add(R.id.searchFrame, fragment1, "searchShape");
        fragmentTransaction.commit();

        excelData = new ExcelData();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case ExcelData.SEARCH_BY_NAME:
                        searchProducts = excelData.getArrayinfo();
                        fragment.setList(searchProducts);
                        dialog.dismiss();
                        break;
                    case ExcelData.SEARCH_INFO:
                        searchProducts = excelData.getArrayinfo();

                        break;
                }
            }
        };
        excelData.setHandler(handler);
        dialog = new LoadingDialog(this);
        dialog.setTextViewText("약 검색 중");
    }

    public void setupToolBar(){
        getSupportActionBar().setTitle("약으로 검색");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
    }

    public void SearchByName(View view) {
        String search = searchEdit.getText().toString();
        /*searchProducts.clear();
        for(ProductInfo p : productInfos){
            if(p.getName().contains(search)){
                searchProducts.add(p);
            }
        }*/
        dialog.show();
        excelData.showInfoByName(search);
        //fragment.setList(searchProducts);
    }

    public void SearchByShape(ArrayList<ProductInfo> list){
        fragment.setList(list);
        tabHost.setCurrentTab(0);
    }

    @Override
    public void changeListView(ArrayList<ProductInfo> list) {
        SearchByShape(list);
    }

    public void addBucket(ProductInfo p){
        bucket.add(p);
        bucket.notifyDataSetChanged();
    }

    public void DrugDetail(ProductInfo p){
        Intent intent = new Intent(this, DrugDetailActivity.class);
        intent.putExtra("drug", p);
        intent.putExtra("where", "SearchDrug");
        startActivityForResult(intent, ADD_BUCKET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_BUCKET){
            if(resultCode == ADD_BUCKET) {
                ProductInfo p = (ProductInfo) data.getSerializableExtra("drug");
                addBucket(p);
            }
        }
    }

    public void addMyTaking(View view) {
        Intent intent = new Intent(this, MyTakingActivity.class);
        intent.putExtra("addDrug", bucketDrug);
        setResult(PUT_IN_BUCKET, intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}

