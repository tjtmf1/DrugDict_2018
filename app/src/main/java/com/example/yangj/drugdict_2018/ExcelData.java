package com.example.yangj.drugdict_2018;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelData {
    DatabaseReference table;
    ArrayList<ProductInfo> pinfo; // 각 약의 정보들을 담은 List
    ArrayList<ProductInfo> arrayinfo;
    int RowEnd; // 엑셀의 끝 항목 번호
    Handler handler;

    static final int GET_BY_NAME = 30;
    static final int SEARCH_BY_NAME = 90;
    static final int SEARCH_INFO = 40;
    static final int NOT_FOUND = -1;
    ExcelData(){
        pinfo = new ArrayList<>();
        arrayinfo = new ArrayList<>();
    }

    public void setHandler(Handler handler){this.handler = handler;}

    public void Excel(Activity activity) { // 엑셀 읽어오는 메서드
        Workbook workbook = null;
        Sheet sheet = null;
        try {
            InputStream inputStream = activity.getResources().getAssets().open("info.xls");
            workbook = Workbook.getWorkbook(inputStream);
            sheet = workbook.getSheet(0);
            int MaxColumn = 2, RowStart = 1, ColumnStart = 2, ColumnEnd = sheet.getRow(2).length - 1;
            RowEnd = sheet.getColumn(MaxColumn - 1).length -1;
            for(int row = RowStart;row <= RowEnd;row++) {
                String pnum = "";
                String pname = sheet.getCell(0, row).getContents();// 품목명 추가
                String pimg = sheet.getCell(2, row).getContents();// 이미지 추가
                String pshape = sheet.getCell(5, row).getContents();// 모양 추가
                String pfcol = sheet.getCell(6, row).getContents();// 색상(앞) 추가
                String pbcol = sheet.getCell(7, row).getContents();// 색상(뒤) 추가
                String pfline = sheet.getCell(8, row).getContents();// 분할선(앞) 추가
                String pbline = sheet.getCell(9, row).getContents();// 분할선(뒤) 추가
                String pcomp = sheet.getCell(1,row).getContents(); // 회사명 입력
                ProductInfo info = new ProductInfo(pnum,pname,pimg,pshape,pfcol,pbcol,pfline,pbline,pcomp);
                pinfo.add(info);
                // 위 정보들을 담은 productinfo 객체를 ArrayList에 넣음.

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
    } // Excel

    public void excelToFirebase(Activity activity){ // 파이어베이스에 낱알정보 데이터 넣기
        Excel(activity);
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        for(int i = 0;i<RowEnd;i++){ //  i<2000 <-데이터베이스에 2000개 데이터 넣기.
            if(!pinfo.get(i).getName().contains(".")
                    ||!pinfo.get(i).getName().contains("$")
                    ||pinfo.get(i).getName().contains("[")
                    ||pinfo.get(i).getName().contains("]")
                    ||pinfo.get(i).getName().contains("#")
                    ||pinfo.get(i).getName().contains("/"))
            table.child(pinfo.get(i).getName()).setValue(pinfo.get(i));
        }


    } // makeDB

    public ArrayList<ProductInfo> searchByInfo(String color, String shape, String line){ // 모든 정보 입력시 일치하는 객체 반환
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo.clear();
                String _line;
                if(line.equals("(-)형")){
                    _line = "-";
                }else if(line.equals("(+)형")){
                    _line = "+";
                }else if(line.equals("없음")){
                    _line = "";
                }
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    if(info.getFrontCol().equals(color) || info.getBackCol().equals(color) || color.equals("전체")){
                        if(info.getShape().equals(shape) || shape.equals("전체")){
                            if(info.getFrontLine().equals(line)){
                                arrayinfo.add(snapshot.getValue(ProductInfo.class));
                            }
                        }
                    }
                }
                handler.sendEmptyMessage(SEARCH_INFO);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return arrayinfo;
    }

    public ArrayList<ProductInfo> searchByImg(String img){ // 이미지 입력시 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getImg().contains(img)){
                        arrayinfo.add(info);
                    }
                }
            } // onSearch

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return arrayinfo;
    }

    public ArrayList<ProductInfo> showInfoByName(String name){ // 약 이름으로 정보 찾기
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getName().contains(name)){
                        arrayinfo.add(info);
                    }
                }
                handler.sendEmptyMessage(SEARCH_BY_NAME);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayinfo;
    }

    public ArrayList<ProductInfo> searchByShape(String shape){// 모양 입력시 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getShape().contains(shape)){
                        arrayinfo.add(info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayinfo;
    }

    public ProductInfo searchByFcol(String frontcolor){ // 전면부 색상 입력시 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getFrontCol().contains(frontcolor)){
                        arrayinfo.add(info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });

        return arrayinfo.get(0);
    }

    public ArrayList<ProductInfo> searchByBcol(String backcolor){ // 후면부 색상 입력시 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getBackCol().contains(backcolor)){
                        arrayinfo.add(info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayinfo;
    }
    public ArrayList<ProductInfo> searchByFline(String frontline){ // 전면부 분할선 입력시 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getImg().contains(frontline)){
                        arrayinfo.add(info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayinfo;
    }
    public ArrayList<ProductInfo> searchByBline(String backline){ // 후면부 분할선 입력시 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayinfo = new ArrayList<>();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getImg().contains(backline)){
                        arrayinfo.add(info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayinfo;
    }

    public ArrayList<ProductInfo> searchByComp(String compname){ // 회사명으로 일치하는 ProductInfo 객체 반환
        arrayinfo.clear();
        table = FirebaseDatabase.getInstance().getReference("MedInfo");
        table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ProductInfo info = snapshot.getValue(ProductInfo.class);
                    //Toast.makeText(MainActivity.this, info.getName(), Toast.LENGTH_SHORT).show(); // 약 정보 들어갔는지 확인.
                    if(info.getImg().contains(compname)){
                        arrayinfo.add(info);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(ExcelData.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayinfo;
    }

    public void SearchByName(String _name){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Medinfo/" + _name);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    ProductInfo p = dataSnapshot.getValue(ProductInfo.class);
                    Message msg = new Message();
                    msg.obj = p;
                    msg.what = GET_BY_NAME;
                    handler.sendMessage(msg);
                } else {
                    handler.sendEmptyMessage(NOT_FOUND);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<ProductInfo> getArrayinfo() {
        return arrayinfo;
    }
}
