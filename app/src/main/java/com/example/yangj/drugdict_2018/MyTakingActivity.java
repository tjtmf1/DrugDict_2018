package com.example.yangj.drugdict_2018;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class MyTakingActivity extends AppCompatActivity {

    static final int START_GALLERY_CHOOSER = 1000;
    static final int START_CAMERA = 1001;
    private static final int CLOUD_VISION = 2000;
    private static final int ADD_PRESCRIPTION = 2001;

    FloatingActionButton drugFAB;
    FloatingActionButton prescriptionFAB;

    ArrayList<com.example.force.infodb.ProductInfo> mDrugs;

    private ListView mDrugList;
    private TakingDrugListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_taking);

        drugFAB = (FloatingActionButton) findViewById(R.id.drugFAB);
        prescriptionFAB = (FloatingActionButton) findViewById(R.id.prescriptionFAB);

        mDrugs = new ArrayList<>();

        mDrugList = (ListView) findViewById(R.id.lvTakingDrugList);
        adapter = new TakingDrugListAdapter(this, R.layout.taking_drug_layout, mDrugs);
        mDrugList.setAdapter(adapter);
    }

    public void onDrugFAB(View view) {
    }

    public void onPrescriptionFAB(View view) {
        Intent intent = new Intent(getApplicationContext(), CloudVisionActivity.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(MyTakingActivity.this);

        builder.setMessage(R.string.dialog_select_prompt);
        builder.setPositiveButton(R.string.dialog_select_gallery, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra("startMode", START_GALLERY_CHOOSER);
                startActivityForResult(intent, CLOUD_VISION);
            }
        });
        builder.setNegativeButton(R.string.dialog_select_camera, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra("startMode", START_CAMERA);
                startActivityForResult(intent, CLOUD_VISION);
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case CLOUD_VISION:
                    Log.d("MyTakingActivity", "finish cloud vision");

                    mDrugs = (ArrayList) data.getSerializableExtra("detectDrugs");

                    Intent intent = new Intent(getApplicationContext(), AddPrescriptionActivity.class);
                    intent.putExtra("detectDrugs", mDrugs);
                    intent.putExtra("imageUri", data.getStringExtra("imageUri"));
                    startActivityForResult(intent, ADD_PRESCRIPTION);

                    break;
                case ADD_PRESCRIPTION:
                    mDrugs = (ArrayList) data.getSerializableExtra("useDrugs");

                    // firebase에 올려주는 작업

                    adapter = new TakingDrugListAdapter(this, R.layout.taking_drug_layout, mDrugs);
                    mDrugList.setAdapter(adapter);

                    break;
            }
        }
    }
}
