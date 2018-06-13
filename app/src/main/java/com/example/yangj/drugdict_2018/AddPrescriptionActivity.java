package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import java.util.ArrayList;

public class AddPrescriptionActivity extends AppCompatActivity {

    private static final String TAG = "AppPrescription";
    private RecyclerView mDrugList;
    private ImageView mMainImage;
    private AddDrugListAdapter adapter;
    private ArrayList<String> drugs;
    private ArrayList<ProductInfo> useDrugs;
    private Handler handler;
    int cnt = 0;

    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        setupToolBar();

        Intent intent = getIntent();
        drugs = (ArrayList) intent.getStringArrayListExtra("detectDrugs");
        uri = intent.getStringExtra("imageUri");

        mDrugList = findViewById(R.id.rvAddDrugs);
        mMainImage = findViewById(R.id.ivPrescription);

        Log.d(TAG, "uri : " + uri);
        mMainImage.setImageURI(Uri.parse(uri));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mDrugList.setLayoutManager(layoutManager);
        useDrugs = new ArrayList<>();
        adapter = new AddDrugListAdapter(drugs);
        mDrugList.setAdapter(adapter);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == ExcelData.GET_BY_NAME){
                    ProductInfo p = (ProductInfo) msg.obj;
                    cnt++;
                }else if(msg.what == ExcelData.NOT_FOUND){
                    cnt++;
                }
                if(cnt == drugs.size()){
                    Intent intent = new Intent(getApplicationContext(), MyTakingActivity.class);
                    intent.putExtra("useDrugs", useDrugs);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    public void setupToolBar(){
        getSupportActionBar().setTitle("처방전 읽기");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_add_prescription, menu);
        return true;
    }

    public void onSave(MenuItem item) {
        ArrayList<Boolean> isChecked = adapter.getCheckced();
        ArrayList<String> name = adapter.getName();

        for(int i=0; i<drugs.size(); i++){
            if(isChecked.get(i)){
                ExcelData excelData = new ExcelData();
                excelData.setHandler(handler);
                excelData.SearchByName(name.get(i));
            }
        }

    }
}
