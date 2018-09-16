package com.dxbwf.wasif.wasif_dx_ball;

// One hit to destroy ...

import android.graphics.Paint;

public class Brick {

    //int l, w;
   // public int point;

    float left,right,top,bottom;
    int color;
    int status=1;
    Paint paint;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setAllPos(float left, float top, float right, float bottom){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom = bottom;
    }

    public Brick(float left, float top, float right, float bottom, int color){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom = bottom;
        this.color=color;
        paint=new Paint();
        this.setPaint(paint);
        getPaint().setColor(color);


    }

}
