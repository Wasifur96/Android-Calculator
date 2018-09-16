package com.dxbwf.wasif.wasif_dx_ball;

 /*
     2 hits to destroy
 */

import android.graphics.Paint;

public class Brick_2 {

    /*public int point_2 ;
    public String colous_2;*/

    float left,right,top,bottom;
    int color, sts;
    Paint paint;
    int status=2;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAllPos(float left, float top, float right, float bottom){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom = bottom;
    }

    public Brick_2(float left, float top, float right, float bottom, int color, int sts){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom = bottom;
        this.color=color;
        this.sts = sts;
        paint=new Paint();
        paint.setColor(color);
        this.setPaint(paint);

    }


}
