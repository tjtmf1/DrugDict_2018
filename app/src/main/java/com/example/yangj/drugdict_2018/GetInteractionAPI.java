package com.example.yangj.drugdict_2018;

import android.content.Intent;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.net.URLEncoder;

public class GetInteractionAPI {
    int page;
    boolean inItem, inName, inIng, inMixName, inMixIng, inProhbt, isTotal;
    String medName, medIng, mixName, mixIng, prohbt;
    final static String key = "toQmUMAQ8Yz04EhUFiPbljHzwxWcbfIi6wnj%2Bttm7FMxLbpIfT1r8csVtspatccoGNGUEwyWYDL7KN8e2OvQLw%3D%3D";
    int totalCount = 0;

    public GetInteractionAPI(){

    }

    public String SearchInteraction(String drugName1, String drugName2) {
        String interaction = "";
        interaction = Search(drugName1, drugName2, 1);
        if (!interaction.equals("")) {
            return interaction;
        } else if (totalCount > 100) {
            int maxPage = (int) Math.ceil(totalCount / 100.0);
            for (int i = 2; i <= maxPage; i++) {
                interaction = Search(drugName1, drugName2, 2);
                if (!interaction.equals("")) {
                    return interaction;
                }
            }
        }
        return interaction;
    }

    private String Search(String drugName1, String drugName2, int page){
        if(drugName1.compareTo(drugName2) > 0){
            String temp = drugName1;
            drugName1 = drugName2;
            drugName2 = temp;
        }
        try {
            String searchDrug = URLEncoder.encode(drugName1);
            URL url = new URL("http://apis.data.go.kr/1470000/DURPrdlstInfoService/getUsjntTabooInfoList?ServiceKey=" +
                    key + "&numOfRows=100&pageNo=" + page +"&itemName=" + searchDrug);
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
                        if(parser.getName().equals("totalCount")){
                            isTotal = true;
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
                        if(isTotal){
                            totalCount = Integer.parseInt(parser.getText());
                            isTotal = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            if(mixName.equals(drugName2)){
                                return prohbt;
                            }
                            inItem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
