package com.example.force.infodb;

import java.io.Serializable;

public class ProductInfo implements Serializable{
    String mName;// 품목명
    String mimg; // 이미지
    String mShape;// 모양
    String mFrontCol;// 색상(앞)
    String mBackCol;// 색상(뒤)
    String mFrontLine;// 분할선(앞)
    String mBackLine;// 분할선(뒤)



    public ProductInfo(String name, String img, String shape, String frontcol,
                       String backcol, String frontline, String backline){
        mName = name;
        mimg = img;
        mShape = shape;
        mFrontCol = frontcol;
        mBackCol = backcol;
        mFrontLine = frontline;
        mBackLine = backline;
    }

    public void setMimg(String mimg) {
        this.mimg = mimg;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmShape(String mShape) {
        this.mShape = mShape;
    }

    public void setmFrontCol(String mFrontCol) {
        this.mFrontCol = mFrontCol;
    }

    public void setmBackCol(String mBackCol) {
        this.mBackCol = mBackCol;
    }

    public void setmFrontLine(String mFrontLine) {
        this.mFrontLine = mFrontLine;
    }

    public void setmBackLine(String mBackLine) {
        this.mBackLine = mBackLine;
    }
    public String getMimg() {
        return mimg;
    }
    public String getmName() {

        return mName;
    }

    public String getmShape() {
        return mShape;
    }

    public String getmFrontCol() {
        return mFrontCol;
    }

    public String getmBackCol() {
        return mBackCol;
    }

    public String getmFrontLine() {
        return mFrontLine;
    }

    public String getmBackLine() {
        return mBackLine;
    }
}
