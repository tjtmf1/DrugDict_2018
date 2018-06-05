package com.example.yangj.drugdict_2018;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class DrugDatabaseActivity extends AppCompatActivity {

    EditText edit;
    EditText edit2;
    TextView status1;
    int page;
    boolean inItem, inName, inIng, inMixName, inMixIng, inProhbt;
    String medName, medIng, mixName, mixIng, prohbt;
    String key = "toQmUMAQ8Yz04EhUFiPbljHzwxWcbfIi6wnj%2Bttm7FMxLbpIfT1r8csVtspatccoGNGUEwyWYDL7KN8e2OvQLw%3D%3D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_database);
        StrictMode.enableDefaults(); // 안드로이드앱 성능 최적화 즉각적인반응을위함
        edit = (EditText) findViewById(R.id.edit);
        edit2 = (EditText) findViewById(R.id.edit2);
        status1 = (TextView) findViewById(R.id.result); //파싱된 결과확인
        inItem = false;inName = false;inIng = false; inMixName = false;
        inMixIng = false; inProhbt = false; page = 1;
        medName = null; medIng = null; mixName = null; mixIng = null; prohbt = null;
        Intent intent = getIntent();
        String str =intent.getStringExtra("Drugs");
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        parseData();
    }

    public void parseData() {
        try {
            //URL url = new URL("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getUsjntTabooInfoList?ServiceKey=dgBh%2B8ZYTUUyqqnGYPCRg9lOwPxjHNdJIvQ7kPgfQYuZI3Uj0B0f85QoBRQ8%2F28wUfYbip%2Fsp4ba3KKYJf%2BjAg%3D%3D"
            //        + "&numOfRows=10&pageNo="+page); //검색 URL부분

            URL url = new URL("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getUsjntTabooInfoList?ServiceKey=" +
                    key + "&numOfRows=10&pageNo=1");
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);
            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("ITEM_NAME")) { //title 만나면 내용을 받을수 있게 하자
                            //System.out.println("");
                            inName = true; //약의 이름
                        }
                        if (parser.getName().equals("INGR_KOR_NAME")) {

                            inIng = true; //약의 성분
                        }
                        if (parser.getName().equals("MIXTURE_ITEM_NAME")) {

                            inMixName = true; //혼용약 이름
                        }
                        if (parser.getName().equals("MIXTURE_INGR_KOR_NAME")) {

                            inMixIng = true; //혼용약 성분
                        }
                        if (parser.getName().equals("PROHBT_CONTENT")) {

                            inProhbt = true; //부작용
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때

                        if (inName) { //isName이 true일 때 태그의 내용을 저장.
                            medName = parser.getText();
                            inName = false;
                        }
                        if (inIng) {
                            medIng = parser.getText();
                            inIng = false;
                        }
                        if (inMixName) {
                            mixName = parser.getText();
                            inMixName = false;
                        }
                        if (inMixIng) {
                            mixIng = parser.getText();
                            inMixIng = false;
                        }
                        if (inProhbt) {
                            prohbt = parser.getText();
                            inProhbt = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            status1.setText(status1.getText() + "약명 : " + medName + "\n 약 성분: " + medIng + "\n 혼용약명 : " + mixName +
                                    "\n 혼용약 성분 : " + mixIng + "\n 부작용 : " + prohbt + "\n");
                            inItem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mOnClick(View v) {
        final String[] data = new String[1];
        switch (v.getId()) {
            case R.id.button:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data[0] = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                status1.setText(data[0]); //TextView에 문자열  data 출력
                            }
                        });
                    }
                }).start();
                break;
        }
    }//mOnClick method

    public String getXmlData() {
        String ans = "";
        StringBuffer buffer = new StringBuffer();
//        String med1 = "스포라녹스캡슐(이트라코나졸)";
//        String med2 = "심바스타정40밀리그램(심바스타틴)";
        String med1 = edit.getText().toString();
        String med2 = edit2.getText().toString();
        String queryUrl = "http://apis.data.go.kr/1470000/DURPrdlstInfoService/getUsjntTabooInfoList?ServiceKey=" +
                key +
                "&numOfRows=10&pageNo=" + String.valueOf(page);

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream(); // url 위치로 입력스트림 연결
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));//inputstream으로부터 xml입력받기
            int eventType = xpp.getEventType();

            boolean CHECK_NAME = false, CHECK_MIXTURE = false, CHECK_PROHBT = false;
            boolean check1 = false, check2 = false;
            boolean CHECK_FIN = false;
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작..\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        String tag_name = xpp.getName();
                        if (tag_name.equals("ITEM_NAME")) {
                            CHECK_NAME = true;
                        }
                        if (tag_name.equals("MIXTURE_ITEM_NAME")) {
                            CHECK_MIXTURE = true;
                        }
                        if (tag_name.equals("PROHBT_CONTENT")) {
                            CHECK_PROHBT = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (CHECK_NAME) {
                            String Data = xpp.getText();
                            if (med1.equals(Data)) {
                                check1 = true;
                            }
                        }
                        if (CHECK_MIXTURE) {
                            String Data = xpp.getText();
                            if (med2.equals(Data)) {
                                check2 = true;
                            }
                        }
                        if (CHECK_PROHBT) {
                            String Data = xpp.getText();
                            if (check1 && check2) {
                                ans = Data;
                                CHECK_FIN = true;
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tag_name = xpp.getName();
                        if (tag_name.equals("ITEM_NAME")) {
                            CHECK_NAME = false;
                        }
                        if (tag_name.equals("MIXTURE_ITEM_NAME")) {
                            CHECK_MIXTURE = false;
                        }
                        if (tag_name.equals("PROHBT_CONTENT")) {
                            CHECK_PROHBT = false;
                        }
                        if (tag_name.equals("MIXTURE_CHANGE_DATE")) {
                            check1 = false;
                            check2 = false;
                        }
                        break;
                }
                eventType = xpp.next();
                if (CHECK_FIN)// 만약 답을 찾았으면 반복문을 빠져나간다
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
}
