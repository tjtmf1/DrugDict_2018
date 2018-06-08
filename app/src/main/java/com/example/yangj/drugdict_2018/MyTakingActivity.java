package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class MyTakingActivity extends AppCompatActivity {

    private static final int START_GALLERY_CHOOSER = 1000;
    private static final int START_CAMERA = 1001;
    private static final int CLOUD_VISION = 2000;
    private static final int ADD_PRESCRIPTION = 2001;

    FloatingActionButton drugFAB;
    FloatingActionButton prescriptionFAB;

    ArrayList<Drug> mDrugs;

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

        // 처방전에서 불러옴
        mDrugs.add(new Drug("asdf"));
        mDrugs.add(new Drug("qwer"));
        mDrugs.add(new Drug("zxcv"));
    }

    public void onDrugFAB(View view) {
    }

    public void onPrescriptionFAB(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MyTakingActivity.this);
//        builder
//                .setMessage("처방전 선택")
//                .setPositiveButton("갤러리",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(getApplicationContext(), CloudVisionActivity.class);
//                                intent.putExtra("startMode", START_GALLERY_CHOOSER);
//                                startActivity(intent);
//                            }
//                        })
//                .setNegativeButton("카메라",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(getApplicationContext(), CloudVisionActivity.class);
//                                intent.putExtra("startMode", START_CAMERA);
//                                startActivity(intent);
//                            }
//                        });
//        builder.create().show();

        Intent intent = new Intent(getApplicationContext(), AddPrescriptionActivity.class);
        intent.putExtra("drugs", mDrugs);
        startActivityForResult(intent, ADD_PRESCRIPTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case CLOUD_VISION:
                    break;
                case ADD_PRESCRIPTION:
                    mDrugs = (ArrayList) data.getSerializableExtra("useDrugs");

                    for(int i=0; i<mDrugs.size(); i++){
                        Log.d("MyTakingActivity", mDrugs.get(i).getmDrugName() + "");
                    }

                    // firebase에 올려주는 작업

                    adapter = new TakingDrugListAdapter(this, R.layout.taking_drug_layout, mDrugs);
                    mDrugList.setAdapter(adapter);

                    //Log.d("MyTakingActivity", mDrugs.get(0).getmDrugName() + "");

                    break;
            }
        }
    }
}
