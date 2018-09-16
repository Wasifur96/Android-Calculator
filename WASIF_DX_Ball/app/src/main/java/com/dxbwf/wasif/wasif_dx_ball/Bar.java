package com.dxbwf.wasif.wasif_dx_ball;


import android.graphics.Paint;

public class Bar extends Drawable {

    //int l, w ;

    //public void changePosition(int x,int y){}

    float left,right,top,bottom;
    int color;
    Paint paint;

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

    /*public void setTop(float top) {
        this.top = top;
    }*/

    public float getBottom() {
        return bottom;
    }

    /*public void setBottom(float bottom) {
        this.bottom = bottom;
    }*/

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Paint getPaint() {
        return paint;
    }

   /* public void setPaint(Paint paint) {
        this.paint = paint;
    }*/

    public Bar(float left, float top, float right, float bottom, int color){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom = bottom;
        this.color=color;
        paint=new Paint();
        paint.setColor(color);
    }

}
