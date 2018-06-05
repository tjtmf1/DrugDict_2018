package com.example.yangj.drugdict_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class SearchDrugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_drug);
        init();
    }

    public void init(){
        //////////////////////////탭바 설정 시작
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.content1);
        tab1.setIndicator("TAB 1");
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.content2);
        tab2.setIndicator("TAB 2");
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3");
        tab3.setContent(R.id.content3);
        tab3.setIndicator("TAB 3");
        tabHost.addTab(tab3);
        tabHost.setCurrentTab(2);
    }
}

