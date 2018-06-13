package com.example.yangj.drugdict_2018;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.api.client.util.Charsets;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;

public class GetInteractionAPI {
    final static String key = "toQmUMAQ8Yz04EhUFiPbljHzwxWcbfIi6wnj%2Bttm7FMxLbpIfT1r8csVtspatccoGNGUEwyWYDL7KN8e2OvQLw%3D%3D";
    int totalCount = 0;
    Context context;
    Handler handler;
    Handler mainHandler;
    String drugName1, drugName2;
    static final int FIRST_PAGE = 7;
    int SEARCH_COMPLETE = 9900;
    static final int NOT_FIRST_PAGE = 43;

    @SuppressLint("HandlerLeak")
    public GetInteractionAPI(Context context) {
        this.context = context;
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.v("test a", "msg.obj : " + msg.obj);
                /*if (msg.what == FIRST_PAGE){
                    if(!msg.obj.equals("")){
                        Message message = handler.obtainMessage();
                        message.what = SEARCH_COMPLETE++;
                        message.obj = msg.obj;
                        Log.v("a", (String) message.obj+String.valueOf(SEARCH_COMPLETE));
                        mainHandler.sendMessage(msg);
                    } else if(totalCount > 100) {
                        //int maxPage = (int) Math.ceil(totalCount / 100.0);
                        //for (int i = 2; i <= maxPage; i++) {
                            Search(drugName1, drugName2, 2);
                        //}
                    } else {
                        Message message = handler.obtainMessage();
                        message.what = SEARCH_COMPLETE++;
                        message.obj = msg.obj;
                        Log.v("B", (String) message.obj+String.valueOf(SEARCH_COMPLETE));
                        mainHandler.sendMessage(msg);
                    }
                } else if(msg.what == NOT_FIRST_PAGE){
                    //if(!msg.obj.equals("")){
                        Message message = handler.obtainMessage();
                        message.what = SEARCH_COMPLETE++;
                        message.obj = msg.obj;
                    Log.v("C", (String) message.obj+String.valueOf(SEARCH_COMPLETE));
                        mainHandler.sendMessage(msg);
                    //}
                }*/
            }
        };
    }

    public void setHandler(Handler _handler){
        mainHandler = _handler;
    }

    public void SearchInteraction(String drugName1, String drugName2) {
        Log.v("test", "SearchInteraction start");
        this.drugName1 = drugName1;
        this.drugName2 = drugName2;
        Search(drugName1, drugName2, 1);
    }

    private void Search(String drugName1, String drugName2, int page) {
        try {
            String searchDrug = URLEncoder.encode(drugName1);
            String url = "http://apis.data.go.kr/1470000/DURPrdlstInfoService/getUsjntTabooInfoList?ServiceKey=" +
                    key + "&numOfRows=100&pageNo=" + page + "&itemName=" + searchDrug;
            Ion.with(context)
                    .load(url)
                    .asString(Charsets.UTF_8).setCallback(new FutureCallback<String>() {
                @Override
                public void onCompleted(Exception e, String result) {
                    try {
                        Log.v("test", "Parsing start");
                        String interaction = "";
                        boolean inItem = false, inName = false, inIng = false, inMixName = false, inMixIng = false, inProhbt = false, isTotal = false;
                        String medName = "", medIng = "", mixName = "", mixIng = "", prohbt = "";
                        XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
                        XmlPullParser parser = parserCreator.newPullParser();
                        parser.setInput(new StringReader(result));
                        int parserEvent = parser.getEventType();
                        System.out.println("파싱시작합니다.");
                        while (parserEvent != XmlPullParser.END_DOCUMENT) {
                            switch (parserEvent) {
                                case XmlPullParser.START_TAG:
                                    if (parser.getName().equals("item")) {
                                        inItem = true;
                                    } else if (inItem && parser.getName().equals("ITEM_NAME")) { //title 만나면 내용을 받을수 있게 하자
                                        //System.out.println("");
                                        inName = true; //약의 이름
                                    } else if (inItem && parser.getName().equals("INGR_KOR_NAME")) {

                                        inIng = true; //약의 성분
                                    } else if (inItem && parser.getName().equals("MIXTURE_ITEM_NAME")) {

                                        inMixName = true; //혼용약 이름
                                    } else if (inItem && parser.getName().equals("MIXTURE_INGR_KOR_NAME")) {

                                        inMixIng = true; //혼용약 성분
                                    } else if (inItem && parser.getName().equals("PROHBT_CONTENT")) {

                                        inProhbt = true; //부작용
                                    }
                                    if (parser.getName().equals("totalCount")) {
                                        isTotal = true;
                                    }
                                    break;

                                case XmlPullParser.TEXT://parser가 내용에 접근했을때

                                    if (inName) { //isName이 true일 때 태그의 내용을 저장.
                                        medName = parser.getText();
                                        inName = false;
                                    } else if (inIng) {
                                        medIng = parser.getText();
                                        inIng = false;
                                    } else if (inMixName) {
                                        mixName = parser.getText();
                                        inMixName = false;
                                    } else if (inMixIng) {
                                        mixIng = parser.getText();
                                        inMixIng = false;
                                    } else if (inProhbt) {
                                        prohbt = parser.getText();
                                        inProhbt = false;
                                    }
                                    if (isTotal) {
                                        totalCount = Integer.parseInt(parser.getText());
                                        isTotal = false;
                                    }
                                    break;
                                case XmlPullParser.END_TAG:
                                    if (parser.getName().equals("item")) {
                                        /*if (mixName.equals(drugName2)) {
                                            if (page == 1) {
                                                Message msg = handler.obtainMessage();
                                                msg.what = FIRST_PAGE;
                                                msg.obj = interaction;
                                                Log.v("d", (String) interaction);
                                                handler.sendMessage(msg);
                                            } else {
                                                Message msg = handler.obtainMessage();
                                                msg.what = NOT_FIRST_PAGE;
                                                msg.obj = interaction;
                                                Log.v("e", (String) interaction);
                                                handler.sendMessage(msg);
                                            }
                                        } else if (mixName.equals(drugName1)) {
                                            if (page == 1) {
                                                Message msg = new Message();
                                                msg.what = FIRST_PAGE;
                                                msg.obj = interaction;
                                                handler.sendMessage(msg);
                                            } else {
                                                Message msg = new Message();
                                                msg.what = NOT_FIRST_PAGE;
                                                msg.obj = interaction;
                                                handler.sendMessage(msg);
                                            }
                                        }*/

                                    }

                                    inItem = false;
                                    break;
                            }
                            parserEvent = parser.next();
                        }
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            });

        } catch(Exception e){
            e.printStackTrace();
        }
        if(page == 1){
            Message msg = new Message();
            msg.what = FIRST_PAGE;
            msg.obj = "";
            handler.sendMessage(msg);
        } else {
            Message msg = new Message();
            msg.what = NOT_FIRST_PAGE;
            msg.obj = "";
            handler.sendMessage(msg);
        }
        Log.v("test", "parse_end");
    }
}
