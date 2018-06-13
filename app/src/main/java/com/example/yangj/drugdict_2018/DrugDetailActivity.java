package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

public class DrugDetailActivity extends AppCompatActivity {
    ProductInfo drug;
    String where;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        Intent intent = getIntent();
        drug = (ProductInfo) intent.getSerializableExtra("drug");
        where = intent.getStringExtra("where");
        setupToolBar();

        //이름으로 drug 찾기.

        ImageView drugImage = (ImageView)findViewById(R.id.detailImage);
        TextView drugName = (TextView)findViewById(R.id.detailName);
        TextView drugCompany = (TextView)findViewById(R.id.detailCompany);
        TextView drugShape = (TextView)findViewById(R.id.detailShape);
        TextView drugColor = (TextView)findViewById(R.id.detailColor);
        //setText 하기.

        Picasso.get().load(drug.getImg()).into(drugImage);
        drugName.setText(drug.getName());
        drugCompany.setText(drug.getCompName());
        drugShape.setText(drug.getShape());
        drugColor.setText(drug.getFrontCol());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(where.equals("MyTaking"))
            return true;
        getMenuInflater().inflate(R.menu.actionbar_drug_detail, menu);
        return true;
    }

    public void setupToolBar(){
        getSupportActionBar().setTitle("약으로 검색");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    public void onSaveBasket(MenuItem item) {
        Intent intent = new Intent();
        intent.putExtra("drug", drug);
        setResult(SearchDrugActivity.ADD_BUCKET, intent);
        Toast.makeText(this, "바구니에 추가되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
