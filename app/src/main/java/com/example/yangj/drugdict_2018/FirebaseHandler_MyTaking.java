package com.example.yangj.drugdict_2018;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class FirebaseHandler_MyTaking {
    DatabaseReference db;
    String uid;
    Handler handler;

    static final int GET_COMPLETE = 100;
    ArrayList<ProductInfo> taking;
    FirebaseHandler_MyTaking(String uid) {
        db = FirebaseDatabase.getInstance().getReference("Taking/" + uid);
    }

    public void addDrug(ProductInfo p){
        db.child(p.getName()).setValue(p);
    }

    public void getAllDrug(){
        taking = new ArrayList<>();
        Query query = db.orderByPriority();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    /*String num = data.child("num").getValue().toString();
                    String name = data.child("name").getValue().toString();
                    String img = data.child("img").getValue().toString();
                    String shape = data.child("shape").getValue().toString();
                    String frontCol = data.child("frontCol").getValue().toString();
                    String backCol = data.child("backCol").getValue().toString();
                    String frontLine = data.child("frontLine").getValue().toString();
                    String backLine = data.child("backLine").getValue().toString();
                    String compName = data.child("compName").getValue().toString();
                    taking.add(new ProductInfo(num, name, img, shape, frontCol, backCol, frontLine, backLine, compName));*/
                    taking.add(data.getValue(ProductInfo.class));
                }
                handler.sendEmptyMessage(GET_COMPLETE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteDrug(String name){
        db.child(name).removeValue();
    }


    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public ArrayList<ProductInfo> getList(){return taking;}
}
