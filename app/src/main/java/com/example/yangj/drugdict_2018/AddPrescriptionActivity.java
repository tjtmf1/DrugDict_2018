package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private ListView mDrugList;
    private ImageView mMainImage;
    private DrugListAdapter adapter;
    private ArrayList<Drug> drugs;
    private ArrayList<Drug> useDrugs;

    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        setupToolBar();

        Intent intent = getIntent();
        drugs = (ArrayList) intent.getSerializableExtra("detectDrugs");
        uri = intent.getStringExtra("imageUri");

        mDrugList = findViewById(R.id.lvAddDrugs);
        mMainImage = findViewById(R.id.ivPrescription);

        Log.d(TAG, "uri : " + uri);
        mMainImage.setImageURI(Uri.parse(uri));
        useDrugs = new ArrayList<>();
        adapter = new DrugListAdapter(this, R.layout.add_drug_layout, drugs);
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

        Intent intent = new Intent();
        intent.putExtra("useDrugs", useDrugs);
        setResult(RESULT_OK, intent);
        finish();
    }
}
