package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class AddPrescriptionActivity extends AppCompatActivity {

    private static final String CLOUD_VISION_API_KEY = "AIzaSyA5ntBn3BaEl7YOPyGiHvoGzL23bZIzads";
    public static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;

    private static final int START_GALLERY_CHOOSER = 1000;
    private static final int START_CAMERA = 1001;

    private static final String TAG = CloudVisionActivity.class.getSimpleName();

    private ListView mDrugList;
    private ImageView mMainImage;
    private DrugSearchListAdapter adapter;
    private ArrayList<Drug> drugs;
    private ArrayList<Drug> useDrugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        setupToolBar();

        Intent intent = getIntent();
        drugs = (ArrayList) intent.getSerializableExtra("detectDrugs");

        mDrugList = findViewById(R.id.lvAddDrugs);
        mMainImage = findViewById(R.id.ivPrescription);
        useDrugs = new ArrayList<>();
        adapter = new DrugSearchListAdapter(this, R.layout.add_drug_layout, drugs);
        mDrugList.setAdapter(adapter);
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
        ArrayList<EditText> editTexts = adapter.getmEditTexts();
        ArrayList<CheckBox> checkBoxes = adapter.getmCheckBoxs();

        for(int i=0; i<drugs.size(); i++){
            if(checkBoxes.get(i).isChecked()){
                useDrugs.add(new Drug(editTexts.get(i).getText().toString()));
            }
        }

//        for(int i=0; i<useDrugs.size(); i++){
//            Log.d("AppPrescriptionActivity", useDrugs.get(i).getmDrugName() + "");
//        }

        Intent intent = new Intent();
        intent.putExtra("useDrugs", useDrugs);
        setResult(RESULT_OK, intent);
        finish();
    }
}
