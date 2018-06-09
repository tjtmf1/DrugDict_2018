package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DrugDetailActivity extends AppCompatActivity {
    com.example.force.infodb.ProductInfo drug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        //이름으로 drug 찾기.

        TextView drugName = (TextView)findViewById(R.id.detailName);
        TextView drugCompany = (TextView)findViewById(R.id.detailCompany);
        TextView drugShape = (TextView)findViewById(R.id.detailShape);
        TextView drugColor = (TextView)findViewById(R.id.detailColor);

        //setText 하기.
    }
}
