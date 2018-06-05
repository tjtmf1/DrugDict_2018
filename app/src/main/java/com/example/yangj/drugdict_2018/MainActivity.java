package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddDrug(View view) {
        Intent intent = new Intent(this, AddDrugActivity.class);
        startActivity(intent);
    }

    public void onAddPrescription(View view) {
        Intent intent = new Intent(this, AddPrescriptionActivity.class);
        startActivity(intent);
    }

    public void onIntro(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onMyTaking(View view) {
        Intent intent = new Intent(this, MyTakingActivity.class);
        startActivity(intent);
    }

    public void onSearchDrug(View view) {
        Intent intent = new Intent(this, SearchDrugActivity.class);
        startActivity(intent);
    }

    public void onSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onCloudVision(View view) {
        Intent intent = new Intent(this, CloudVisionActivity.class);
        startActivity(intent);
    }

    public void onDrugDatabase(View view) {
        Intent intent = new Intent(this, DrugDatabaseActivity.class);
        startActivity(intent);
    }
}
