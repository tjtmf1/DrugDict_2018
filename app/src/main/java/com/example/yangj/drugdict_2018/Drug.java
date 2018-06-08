package com.example.yangj.drugdict_2018;

import java.io.Serializable;

public class Drug implements Serializable{
    String mDrugName;


    public Drug(String mDrugName) {
        this.mDrugName = mDrugName;
    }

    public String getmDrugName() {
        return mDrugName;
    }

    public void setmDrugName(String mDrugName) {
        this.mDrugName = mDrugName;
    }
}
