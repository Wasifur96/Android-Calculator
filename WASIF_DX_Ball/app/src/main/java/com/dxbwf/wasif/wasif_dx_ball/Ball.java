package com.dxbwf.wasif.wasif_dx_ball;


import android.graphics.Paint;

public class Ball extends Drawable {

   /* public void Colides_with(Drawable d)
    {}*/

    private int x;
    private int y;
    private int color;
    private int radious;
    private int dx;
    private int dy;
    private Paint paint;

    public Ball(int x,int y,int color_,int radoius_){
        this.x=x;
        this.y=y;
        this.color=color_;
        this.radious=radoius_;
        this.color = color_;
        paint=new Paint();
        paint.setColor(color);
        dx=-0;
        dy=-0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRadious() {
        return radious;
    }

    public void setRadious(int radious) {
        this.radious = radious;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) { this.dx = dx; }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void move(){ x = x + dx;
                         y = y + dy; }

}
