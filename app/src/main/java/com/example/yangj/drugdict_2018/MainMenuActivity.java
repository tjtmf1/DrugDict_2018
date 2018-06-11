package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {
    static String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent gIntent = getIntent();
        uid = gIntent.getStringExtra("uid");
        Intent intent = new Intent(this, MyTakingActivity.class);
        startActivity(intent);
    }
}
