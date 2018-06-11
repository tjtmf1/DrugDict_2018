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
//                    taking.add(new ProductInfo(data.child("mName").getValue().toString()));
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
