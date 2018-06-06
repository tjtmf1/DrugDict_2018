package com.example.yangj.drugdict_2018;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;

public class MyTakingActivity extends AppCompatActivity {

    private static final int START_GALLERY_CHOOSER = 1000;
    private static final int START_CAMERA = 1001;

    FloatingActionButton drugFAB;
    FloatingActionButton prescriptionFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_taking);

        drugFAB = (FloatingActionButton) findViewById(R.id.drugFAB);
        prescriptionFAB = (FloatingActionButton) findViewById(R.id.prescriptionFAB);
    }

    public void onDrugFAB(View view) {
    }

    public void onPrescriptionFAB(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyTakingActivity.this);
        builder
                .setMessage("처방전 선택")
                .setPositiveButton("갤러리",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), AddPrescriptionActivity.class);
                                intent.putExtra("startMode", START_GALLERY_CHOOSER);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("카메라",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), AddPrescriptionActivity.class);
                                intent.putExtra("startMode", START_CAMERA);
                                startActivity(intent);
                            }
                        });
        builder.create().show();
    }
}
