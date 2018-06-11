package com.example.yangj.drugdict_2018;

import java.io.Serializable;

public class ProductInfo implements Serializable {
    String num;
    String name;// 품목명
    String img; // 이미지
    String shape;// 모양
    String frontCol;// 색상(앞)
    String backCol;// 색상(뒤)
    String frontLine;// 분할선(앞)
    String backLine;// 분할선(뒤)
    String compName; // 회사명

    public ProductInfo() {
    }



    public ProductInfo(String num, String name, String img, String shape, String frontcol,
                       String backcol, String frontline, String backline, String compName) {
        this.num = num;
        this.name = name;
        this.img = img;
        this.shape = shape;
        this.frontCol = frontcol;
        this.backCol = backcol;
        this.frontLine = frontline;
        this.backLine = backline;
        this.compName = compName;

    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getFrontCol() {
        return frontCol;
    }

    public void setFrontCol(String frontCol) {
        this.frontCol = frontCol;
    }

    public String getBackCol() {
        return backCol;
    }

    public void setBackCol(String backCol) {
        this.backCol = backCol;
    }

    public String getFrontLine() {
        return frontLine;
    }

    public void setFrontLine(String frontLine) {
        this.frontLine = frontLine;
    }

    public String getBackLine() {
        return backLine;
    }

    public void setBackLine(String backLine) {
        this.backLine = backLine;
    }
    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }
}
