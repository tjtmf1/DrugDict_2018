package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.force.infodb.ProductInfo;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class DrugDetailActivity extends AppCompatActivity {
    com.example.force.infodb.ProductInfo drug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        Intent intent = getIntent();
        drug = (ProductInfo) intent.getSerializableExtra("drug");

        //이름으로 drug 찾기.

        ImageView drugImage = (ImageView)findViewById(R.id.detailImage);
        TextView drugName = (TextView)findViewById(R.id.detailName);
        TextView drugCompany = (TextView)findViewById(R.id.detailCompany);
        TextView drugShape = (TextView)findViewById(R.id.detailShape);
        TextView drugColor = (TextView)findViewById(R.id.detailColor);
        //setText 하기.

        Picasso.get().load(drug.getMimg()).into(drugImage);
        drugName.setText(drug.getmName());
        //drugCompany.setText(drug.getmCompany);
        drugShape.setText(drug.getmShape());
        //drugColor.setText(drug.getmColor);

    }

    public void addDrugInBucket(View view) {
        Intent intent = new Intent();
        intent.putExtra("drug", drug);
        setResult(SearchDrugActivity.ADD_BUCKET, intent);
        Toast.makeText(this, "바구니에 추가되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
