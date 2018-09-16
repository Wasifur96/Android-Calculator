package com.dxbwf.wasif.wasif_dx_ball;

        import android.content.Context;
        import android.content.pm.ActivityInfo;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Paint.Style;
        import android.graphics.PorterDuff;
        import android.support.annotation.NonNull;
        import android.view.KeyEvent;
        import android.view.MotionEvent;
        import android.view.View;

        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.Iterator;
        import java.util.List;
        import java.util.ListIterator;

        import static com.dxbwf.wasif.wasif_dx_ball.MainActivity.con;

public class GameCanvas extends View {



    Staging stage1;
    Ball ball;
    Bar bar;
    Thread ballThread, barThread;
    Paint textFont , textFont2;

    Brick bricks2,bricks3,bricks5,bricks6,bricks10,bricks14;
    //Brick [] b1= new Brick[6];
    Brick_2 bricks7,bricks8,bricks9,bricks11,bricks12,bricks13;
    //Brick_2 [] b2= new Brick_2[6];
    Brick_3 bricks4;

    Helper helper;
    int color = R.color.red;
    int score = 0, total_score=0;
    int life = 3;
    int level = 1;
    int stop_check = 0;    // flag
    public int height_,width_;
    Paint paint;
    float clicked, unClicked;
    float xDelta = 0, a=0;
    public int speed = 8;
    float barWidth = 200;
    float barLeft, barRight, barTop, barBottom;
    float bricksWidth = 80;
    float bricksLeft, bricksRight, bricksTop, bricksBottom;
    float x=0,y=0;
    boolean GameOver =false;
    boolean firstTime=true;
    int brick1color,brick2color,brick3color,brick4color;
    int brck2_status= 2 , brck3_status=3 ;

    public void setBarValue(int keyCode)
    {
        if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT)
            this.x+=2;

    }

    protected void calculateNextPos(){}

    protected void onDraw(Canvas canvas) {
        //speed++;
        height_ = canvas.getHeight();
        width_=canvas.getWidth();
        brick1color=Color.rgb(66,31,112);
        brick2color=Color.rgb(204,153,255);
        brick3color=Color.rgb(0,255,0);
        brick4color=Color.rgb(255,0,0);

        if(firstTime)  // initializing ...
        {
            firstTime=false;
            x=canvas.getWidth() / 2;
            y=canvas.getHeight() / 2;

            textFont=new Paint();
            textFont.setColor(Color.BLACK);
            textFont.setTextSize(40);
            textFont.setFakeBoldText(true);

            textFont2 =new Paint();
            textFont2.setColor(Color.BLACK);
            textFont2.setTextSize(60);
            textFont2.setFakeBoldText(true);

            ball=new Ball(width_/2,height_/2,Color.rgb(106, 38, 165),25);
            ball.setDx(speed);
            ball.setDy(-speed);


            barLeft = getWidth() / 2 - (barWidth / 2);
            barTop = getHeight() - 40;
            barRight = getWidth() / 2 + (barWidth / 2);
            barBottom = getHeight()-20;
            bar = new Bar(barLeft, barTop, barRight, barBottom, R.color.colorPrimaryDark); // instantiating the Bar

            bricksLeft = canvas.getWidth() / 2 - (bricksWidth / 2);
            bricksTop = canvas.getHeight() - 40;
            bricksRight = canvas.getWidth() / 2 + (bricksWidth / 2);
            bricksBottom = canvas.getHeight()-20;



            if(level==1){
                /*for(int i=0;i<6; i++ )
                {
                    b1[i] = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom, brick4color);

                    b2[i] = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color);
                }
                bricks14 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom,brick4color);*/

                //***********************************************************************************/

                bricks10 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom, brick4color);
                /////////////////////////////////////
                bricks7 = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck2_status);
                bricks8 = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck2_status);
                bricks9 = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck2_status);
                /////////////////////////////////////
                bricks2 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom, brick4color);
                bricks3 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom, brick4color);
                bricks4 = new Brick_3 (bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck3_status);
                bricks5 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom, brick4color);
                bricks6 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom, brick4color);
                ////////////////////////////////////
                bricks11 = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck2_status);
                bricks12 = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck2_status);
                bricks13 = new Brick_2(bricksLeft, bricksTop, bricksRight, bricksBottom, brick1color,brck2_status);
                //////////////////////////////////////
                bricks14 = new Brick(bricksLeft, bricksTop, bricksRight, bricksBottom,brick4color);
                //**********************************************************************************//
                this.level_1();

            }else if(level==2){
                this.level_2();
            }else if(level==2 && GameOver){
                this.gameOver();
                speed=0;

                ball.setX(width_/2);
                ball.setY(height_/2+250);
                canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadious(), ball.getPaint() );

                // Result:
                canvas.drawText("Game Over", (canvas.getWidth() / 2)-150, (canvas.getHeight() / 2) - 150, textFont2);
                canvas.drawText("Level "+String.valueOf(level) +" Score: " + String.valueOf(score), (canvas.getWidth() / 2)-150, (canvas.getHeight() / 2), textFont2);
                //canvas.drawText("Total Score: " + String.valueOf(total_score), (canvas.getWidth() / 2)-150, (canvas.getHeight() / 2)+100, textFont2);

            }
        }
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        calculateNextPos();
        canvas.drawRGB(100, 100, 100);
        paint.setColor(Color.RED);
        paint.setStyle(Style.FILL);

        if(!GameOver){
            canvas.drawCircle(ball.getX(),ball.getY(),ball.getRadious(),ball.getPaint());
        }else {
            speed=0;
            canvas.drawCircle(ball.getX(),ball.getY(),ball.getRadious(),ball.getPaint());
            ball.setX(width_/2);
            ball.setY(height_/2+250);

            // Result:
            canvas.drawText("Game Over", (canvas.getWidth() / 2)-150, ( canvas.getHeight() / 2)- 150, textFont2);
            canvas.drawText("At Level: " + String.valueOf(level), (canvas.getWidth() / 2)-150, (canvas.getHeight() / 2), textFont2);
            canvas.drawText("Level " + String.valueOf(level)+ " Score: " + String.valueOf(score), canvas.getWidth() / 2 - 100, (canvas.getHeight() / 2)+100 , textFont2);
            canvas.drawText("Total Score: " + String.valueOf(total_score), (canvas.getWidth() / 2)-150, (canvas.getHeight()/ 2)+ 200, textFont2);

        }


        ballThread= new Thread( new Runnable(){public void run(){ball.move();} } );
        ballThread.start();


        this.ball_colide4(canvas);   // bottom collide
        this.ball_colide1(canvas);  // boundary collide
        canvas.drawRect(bar.getLeft(), bar.getTop(),bar.getRight(),bar.getBottom(), bar.getPaint());   // Drawing Bar
        this.ball_colide2(); // bar collide

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////// D R A W I N G   B r i c k s //////////////////////////////////////////////////////


        /*for(int i=0;i<6; i++ )
        {
            canvas.drawRect(b1[i].getLeft(), b1[i].getTop(), b1[i].getRight(), b1[i].getBottom(), b1[i].getPaint());
            this.ball_colide3(b1[i]); // Check brick type one collide

            canvas.drawRect(b2[i].getLeft(), b2[i].getTop(), b2[i].getRight(), b2[i].getBottom(), b2[i].getPaint());
            this.ball_colide5(b2[i]); // Check brick type two collide
        }

        canvas.drawRect(bricks4.getLeft(), bricks4.getTop(), bricks4.getRight(), bricks4.getBottom(), bricks4.getPaint());
        this.ball_colide6(bricks4); */ // b_type 3

        ///

        canvas.drawRect(bricks2.getLeft(), bricks2.getTop(), bricks2.getRight(), bricks2.getBottom(), bricks2.getPaint());
        this.ball_colide3(bricks2); //brick collide

        canvas.drawRect(bricks3.getLeft(), bricks3.getTop(), bricks3.getRight(), bricks3.getBottom(), bricks3.getPaint());
        this.ball_colide3(bricks3); //brick collide
        canvas.drawRect(bricks4.getLeft(), bricks4.getTop(), bricks4.getRight(), bricks4.getBottom(), bricks4.getPaint());
        this.ball_colide6(bricks4);  // b_type 3
        canvas.drawRect(bricks5.getLeft(), bricks5.getTop(), bricks5.getRight(), bricks5.getBottom(), bricks5.getPaint());
        this.ball_colide3(bricks5);

        canvas.drawRect(bricks6.getLeft(), bricks6.getTop(), bricks6.getRight(), bricks6.getBottom(), bricks6.getPaint());
        this.ball_colide3(bricks6);
        canvas.drawRect(bricks7.getLeft(), bricks7.getTop(), bricks7.getRight(), bricks7.getBottom(), bricks7.getPaint());
        this.ball_colide5(bricks7);
        canvas.drawRect(bricks8.getLeft(), bricks8.getTop(), bricks8.getRight(), bricks8.getBottom(), bricks8.getPaint());
        this.ball_colide5( bricks8);
        canvas.drawRect(bricks9.getLeft(), bricks9.getTop(), bricks9.getRight(), bricks9.getBottom(), bricks9.getPaint());
        this.ball_colide5( bricks9);
        canvas.drawRect(bricks10.getLeft(), bricks10.getTop(), bricks10.getRight(), bricks10.getBottom(), bricks10.getPaint());
        this.ball_colide3(bricks10);
        canvas.drawRect(bricks11.getLeft(), bricks11.getTop(), bricks11.getRight(), bricks11.getBottom(), bricks11.getPaint());
        this.ball_colide5(bricks11);
        canvas.drawRect(bricks12.getLeft(), bricks12.getTop(), bricks12.getRight(), bricks12.getBottom(), bricks12.getPaint());
        this.ball_colide5(bricks12);
        canvas.drawRect(bricks13.getLeft(), bricks13.getTop(), bricks13.getRight(), bricks13.getBottom(), bricks13.getPaint());
        this.ball_colide5(bricks13);
        canvas.drawRect(bricks14.getLeft(), bricks14.getTop(), bricks14.getRight(), bricks14.getBottom(), bricks14.getPaint());
        this.ball_colide3(bricks14);


        // Score Bar:
        canvas.drawText("Score: " + String.valueOf(score), canvas.getWidth() / 2 - 500, canvas.getHeight() / 2 - 900, textFont);
        canvas.drawText("Life: " + String.valueOf(life), canvas.getWidth() / 2 - 300, canvas.getHeight() / 2 - 900, textFont);
        canvas.drawText("Level: " + String.valueOf(level), canvas.getWidth() / 2 - 50, canvas.getHeight() / 2 - 900, textFont);


        invalidate();

    }


// ****************************************  OnDraw() ends here  ***********************************************************************


///////////////////////////////////////////// ------------------------------- ///////////////////////////////////////////////////////////////////
////////////////////////////////////////////          C O L I D E S          //////////////////////////////////////////////////////////////

    public void ball_colide1(Canvas canvas){//boundary collide

        if(ball.getX()+ball.getRadious() >= canvas.getWidth() || ball.getX()-ball.getRadious() <= 0){
            ball.setDx(-ball.getDx());
            //if(ck==2){ck=1;}else if(ck==1){ck=0;}
            stop_check=0;
        }
        if(  ball.getY()-ball.getRadious() <= 0){
            ball.setDy(-ball.getDy());
            //if(ck==2){ck=1;}else if(ck==1){ck=0;}
            stop_check=0;
        }
    }

    public void ball_colide2(){//bar collide
        if( ((ball.getY() + ball.getRadious()) >= bar.getTop()) && ((ball.getY()+ball.getRadious()) <= bar.getBottom()) && ((ball.getX()+ball.getRadious()) >= bar.getLeft()) && ((ball.getX()-ball.getRadious()) <= bar.getRight()))
        {
            ball.setDy(-(ball.getDy()));
           // if(ck==2){ck=1;}else if(ck==1){ck=0;}
            stop_check=0;
        }
    }
    public void ball_colide3(Brick bricks){ //brick_1 collide
        if( ((ball.getY() + ball.getRadious()) >= bricks.getTop()) &&((ball.getY()- ball.getRadious()) <= bricks.getBottom()) && ((ball.getX()+ball.getRadious()) >= bricks.getLeft()) && ((ball.getX()-ball.getRadious()) <= bricks.getRight()))
        {
            score += 1;
            if(score==20 && level==1) {
                total_score = total_score + score;
                score =0;
                level=2;
                firstTime=true;
                ball.setX(width_/2);
                ball.setY(height_/2+250);
            }else if(score==6 && level==2)
            {
                //score+=1;
                total_score = total_score + score;
                GameOver=true;
            }

            ball.setDy(-(ball.getDy()));
            bricks.setLeft(-100);
            bricks.setRight(-50);
            bricks.setTop(0);
            bricks.setBottom(0);


        }
    }
    public void ball_colide5(Brick_2 bricks){//Brick_2 collide
        if (((ball.getY() + ball.getRadious()) >= bricks.getTop()) &&((ball.getY()- ball.getRadious()) <= bricks.getBottom()) && ((ball.getX()+ball.getRadious()) >= bricks.getLeft()) && ((ball.getX()-ball.getRadious()) <= bricks.getRight()))
        {
            if(bricks.getStatus()==2)
            {
                score += 1;
                stop_check= 1;
                bricks.setColor(brick4color);
                bricks.getPaint().setColor(brick4color);

                ball.setDy(-(ball.getDy()));
                bricks.setStatus(1);
            }
            else if(stop_check!=1 && bricks.getStatus()==1)
            {
                score += 1;
                if(score==20 && level==1) {
                    total_score = total_score + score;
                    score =0;
                    level=2;
                    this.level_2();
                    ball.setX(width_/2);
                    ball.setY(height_/2+250);
                }else if(score!=20 && level==1)
                {
                     ball.setDy(-(ball.getDy()));

                }
                else if(score==6&& level==2){
                   // score+=1;
                    GameOver=true;
                    total_score = total_score + score;
                }else if(score!=6 && level==2) {

                    ball.setDy(-(ball.getDy()));

                }
                bricks.setLeft(-100);
                bricks.setRight(-50);
                bricks.setTop(0);
                bricks.setBottom(0);
            }



           // ball.setDy(-(ball.getDy()));
        }

    }
    private void ball_colide6(Brick_3 bricks) {  //Brick_3 collide
        if (((ball.getY() + ball.getRadious()) >= bricks.getTop()) &&((ball.getY()- ball.getRadious()) <= bricks.getBottom()) && ((ball.getX()+ball.getRadious()) >= bricks.getLeft()) && ((ball.getX()-ball.getRadious()) <= bricks.getRight()))
        {
            if(bricks.getStatus()==3){
                score += 1;
                ball.setDy(-(ball.getDy()));
                bricks.setStatus(2);
                bricks.setColor(brick1color);
                bricks.getPaint().setColor(brick1color);
                stop_check=1;

            }else if(bricks.getStatus()==2 && stop_check!=1){
                stop_check=1;
                score += 1;
                ball.setDy(-(ball.getDy()));
                bricks.setStatus(1);
                bricks.setColor(brick4color);
                bricks.getPaint().setColor(brick4color);


            }else if(bricks.getStatus()==1 && stop_check!=1){
                score += 1;
                if(score==2 && level==1) {
                    total_score = total_score + score;
                    score =0;
                    level=2;
                    this.level_2();
                    ball.setX(width_/2);
                    ball.setY(height_/2+250);
                }else if(score!=20 && level==1)
                {
                   ball.setDy(-(ball.getDy()));

                }else if(score==6 && level==2){
                    //score+=1;
                    total_score = total_score + score;
                    GameOver=true;
                }else if(score!=6 && level==2) {

                    ball.setDy(-(ball.getDy()));
                }

                bricks.setLeft(-100);
                bricks.setRight(-50);
                bricks.setTop(0);
                bricks.setBottom(0);
            }

        }

    }
    public void ball_colide4(Canvas canvas){ //bottom collide
        if  ((ball.getY() - ball.getRadious()) >= canvas.getHeight()){

            if(life<=1){
               //if(ck==2){ck=1;}else if(ck==1){ck=0;}
                stop_check=0;
                life-=1;
                GameOver=true;

            }else{
                //if(ck==2){ck=1;}else if(ck==1){ck=0;}
                stop_check=0;
                life-=1;
                ball.setX(canvas.getWidth()/2);
                ball.setY(canvas.getHeight()/2+250);
                ball.setDy(-ball.getDy());

            }
        }
    }
///////////////////////////////////////////////////// --- X --- /////////////////////////////////////////////////////////////////

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                clicked = ev.getX();
                xDelta = bar.getLeft();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                unClicked = ev.getX();
                a = unClicked - clicked;
                if(width_+10 >= xDelta + a +barWidth  && xDelta + a >= -10)
                {
                    bar.setLeft(xDelta + a);
                    bar.setRight(xDelta + a + barWidth);
                }

                break;
            default:
                break;
        }
        return true;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////// ---- Setting Values ---- /////////////////////////////////////////////////////////////////////////
    //// **** L E V E L   1  ***** ////

    public void level_1(){
        /*b1[0].setAllPos(b1[0].getLeft()+  helper.getConfigValue(con,"1brick10left" ),
                b1[0].getTop()+   helper.getConfigValue(con,"1brick10top"  ),
                b1[0].getRight()+ helper.getConfigValue(con,"1brick10right"),
                b1[0].getBottom()+helper.getConfigValue(con,"1brick10bottom") );
        ///////////////////////////////////////////////////////////////////////////////////////
        b2[0].setAllPos(b2[0].getLeft()+  helper.getConfigValue(con,"1brick7left"),
                b2[0].getTop()+   helper.getConfigValue(con,"1brick7top"),
                b2[0].getRight()+ helper.getConfigValue(con,"1brick7right"),
                b2[0].getBottom()+helper.getConfigValue(con,"1brick7bottom"));

        b2[1].setAllPos(b2[1].getLeft()+  helper.getConfigValue(con,"1brick8left"),
                b2[1].getTop()+   helper.getConfigValue(con,"1brick8top"),
                b2[1].getRight()+ helper.getConfigValue(con,"1brick8right"),
                b2[1].getBottom()+helper.getConfigValue(con,"1brick8bottom"));

        b2[2].setAllPos(b2[2].getLeft()+  helper.getConfigValue(con,"1brick9left"),
                b2[2].getTop()+   helper.getConfigValue(con,"1brick9top"),
                b2[2].getRight()+ helper.getConfigValue(con,"1brick9right"),
                b2[2].getBottom()+helper.getConfigValue(con,"1brick9bottom"));
        //////////////////////////////////////////////////////////////////////////////////////
        b1[1].setAllPos( b1[1].getLeft()+  helper.getConfigValue(con,"1brick2left"),
                b1[1].getTop()+   helper.getConfigValue(con,"1brick2top"),
                b1[1].getRight()+ helper.getConfigValue(con,"1brick2right"),
                b1[1].getBottom()+helper.getConfigValue(con,"1brick2bottom"));

        b1[2].setAllPos(b1[2].getLeft()+  helper.getConfigValue(con,"1brick3left"),
                b1[2].getTop()+   helper.getConfigValue(con,"1brick3top"),
                b1[2].getRight()+ helper.getConfigValue(con,"1brick3right"),
                b1[2].getBottom()+helper.getConfigValue(con,"1brick3bottom"));

        bricks4.setAll(bricks4.getLeft()+  helper.getConfigValue(con,"1brick4left"),
                bricks4.getTop()+   helper.getConfigValue(con,"1brick4top"),
                bricks4.getRight()+ helper.getConfigValue(con,"1brick4right"),
                bricks4.getBottom()+helper.getConfigValue(con,"1brick4bottom"));

        b1[3].setAllPos(b1[3].getLeft()+  helper.getConfigValue(con,"1brick5left"),
                b1[3].getTop()+   helper.getConfigValue(con,"1brick5top"),
                b1[3].getRight()+ helper.getConfigValue(con,"1brick5right"),
                b1[3].getBottom()+helper.getConfigValue(con,"1brick5bottom"));

        b1[4].setAllPos(b1[4].getLeft()+  helper.getConfigValue(con,"1brick6left"),
                b1[4].getTop()+   helper.getConfigValue(con,"1brick6top"),
                b1[4].getRight()+ helper.getConfigValue(con,"1brick6right"),
                b1[4].getBottom()+helper.getConfigValue(con,"1brick6bottom"));
        /////////////////////////////////////////////////////////////////////////////////////////
        b2[3].setAllPos(b2[3].getLeft()+  helper.getConfigValue(con,"1brick11left"),
                b2[3].getTop()+   helper.getConfigValue(con,"1brick11top"),
                b2[3].getRight()+ helper.getConfigValue(con,"1brick11right"),
                b2[3].getBottom()+helper.getConfigValue(con,"1brick11bottom"));

        b2[4].setAllPos(b2[4].getLeft()+  helper.getConfigValue(con,"1brick12left"),
                b2[4].getTop()+   helper.getConfigValue(con,"1brick12top"),
                b2[4].getRight()+ helper.getConfigValue(con,"1brick12right"),
                b2[4].getBottom()+helper.getConfigValue(con,"1brick12bottom"));

        b2[5].setAllPos(b2[5].getLeft()+  helper.getConfigValue(con,"1brick13left"),
                b2[5].getTop()+   helper.getConfigValue(con,"1brick13top"),
                b2[5].getRight()+ helper.getConfigValue(con,"1brick13right"),
                b2[5].getBottom()+helper.getConfigValue(con,"1brick13bottom"));
        //////////////////////////////////////////////////////////////////////////////////////////
        b1[5].setAllPos(b1[5].getLeft()+  helper.getConfigValue(con,"1brick14left"),
                b1[5].getTop()+   helper.getConfigValue(con,"1brick14top"),
                b1[5].getRight()+ helper.getConfigValue(con,"1brick14right"),
                b1[5].getBottom()+helper.getConfigValue(con,"1brick14bottom")); */

        ///

        bricks10.setAllPos(bricks10.getLeft()+  helper.getConfigValue(con,"1brick10left"),
                bricks10.getTop()+   helper.getConfigValue(con,"1brick10top"),
                bricks10.getRight()+ helper.getConfigValue(con,"1brick10right"),
                bricks10.getBottom()+helper.getConfigValue(con,"1brick10bottom"));
        ///////////////////////////////////////////////////////////////////////////////////////
        bricks7.setAllPos(bricks7.getLeft()+  helper.getConfigValue(con,"1brick7left"),
                bricks7.getTop()+   helper.getConfigValue(con,"1brick7top"),
                bricks7.getRight()+ helper.getConfigValue(con,"1brick7right"),
                bricks7.getBottom()+helper.getConfigValue(con,"1brick7bottom"));

        bricks8.setAllPos(bricks8.getLeft()+  helper.getConfigValue(con,"1brick8left"),
                bricks8.getTop()+   helper.getConfigValue(con,"1brick8top"),
                bricks8.getRight()+ helper.getConfigValue(con,"1brick8right"),
                bricks8.getBottom()+helper.getConfigValue(con,"1brick8bottom"));

        bricks9.setAllPos(bricks9.getLeft()+  helper.getConfigValue(con,"1brick9left"),
                bricks9.getTop()+   helper.getConfigValue(con,"1brick9top"),
                bricks9.getRight()+ helper.getConfigValue(con,"1brick9right"),
                bricks9.getBottom()+helper.getConfigValue(con,"1brick9bottom"));
        //////////////////////////////////////////////////////////////////////////////////////
        bricks2.setAllPos(bricks2.getLeft()+  helper.getConfigValue(con,"1brick2left"),
                bricks2.getTop()+   helper.getConfigValue(con,"1brick2top"),
                bricks2.getRight()+ helper.getConfigValue(con,"1brick2right"),
                bricks2.getBottom()+helper.getConfigValue(con,"1brick2bottom"));

        bricks3.setAllPos(bricks3.getLeft()+  helper.getConfigValue(con,"1brick3left"),
                bricks3.getTop()+   helper.getConfigValue(con,"1brick3top"),
                bricks3.getRight()+ helper.getConfigValue(con,"1brick3right"),
                bricks3.getBottom()+helper.getConfigValue(con,"1brick3bottom"));

        bricks4.setAll(bricks4.getLeft()+  helper.getConfigValue(con,"1brick4left"),
                bricks4.getTop()+   helper.getConfigValue(con,"1brick4top"),
                bricks4.getRight()+ helper.getConfigValue(con,"1brick4right"),
                bricks4.getBottom()+helper.getConfigValue(con,"1brick4bottom"));

        bricks5.setAllPos(bricks5.getLeft()+  helper.getConfigValue(con,"1brick5left"),
                bricks5.getTop()+   helper.getConfigValue(con,"1brick5top"),
                bricks5.getRight()+ helper.getConfigValue(con,"1brick5right"),
                bricks5.getBottom()+helper.getConfigValue(con,"1brick5bottom"));

        bricks6.setAllPos(bricks6.getLeft()+  helper.getConfigValue(con,"1brick6left"),
                bricks6.getTop()+   helper.getConfigValue(con,"1brick6top"),
                bricks6.getRight()+ helper.getConfigValue(con,"1brick6right"),
                bricks6.getBottom()+helper.getConfigValue(con,"1brick6bottom"));
        /////////////////////////////////////////////////////////////////////////////////////////
        bricks11.setAllPos(bricks11.getLeft()+  helper.getConfigValue(con,"1brick11left"),
                bricks11.getTop()+   helper.getConfigValue(con,"1brick11top"),
                bricks11.getRight()+ helper.getConfigValue(con,"1brick11right"),
                bricks11.getBottom()+helper.getConfigValue(con,"1brick11bottom"));

        bricks12.setAllPos(bricks12.getLeft()+  helper.getConfigValue(con,"1brick12left"),
                bricks12.getTop()+   helper.getConfigValue(con,"1brick12top"),
                bricks12.getRight()+ helper.getConfigValue(con,"1brick12right"),
                bricks12.getBottom()+helper.getConfigValue(con,"1brick12bottom"));

        bricks13.setAllPos(bricks13.getLeft()+  helper.getConfigValue(con,"1brick13left"),
                bricks13.getTop()+   helper.getConfigValue(con,"1brick13top"),
                bricks13.getRight()+ helper.getConfigValue(con,"1brick13right"),
                bricks13.getBottom()+helper.getConfigValue(con,"1brick13bottom"));
        //////////////////////////////////////////////////////////////////////////////////////////
        bricks14.setAllPos(bricks14.getLeft()+  helper.getConfigValue(con,"1brick14left"),
                bricks14.getTop()+   helper.getConfigValue(con,"1brick14top"),
                bricks14.getRight()+ helper.getConfigValue(con,"1brick14right"),
                bricks14.getBottom()+helper.getConfigValue(con,"1brick14bottom"));

    }

    // **************************************************  L E V E L  2   **************************************************************** //

    private void level_2() {
        /*b1[0].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick10left"),
                bricksTop+   helper.getConfigValue(con,"2brick10top"),
                bricksRight+ helper.getConfigValue(con,"2brick10right"),
                bricksBottom+helper.getConfigValue(con,"2brick10bottom"));
///////////////////////////////////////////////////////////////////////////////////////
        b2[0].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick7left"),
                bricksTop+   helper.getConfigValue(con,"2brick7top"),
                bricksRight+ helper.getConfigValue(con,"2brick7right"),
                bricksBottom+helper.getConfigValue(con,"2brick7bottom"));

        b2[1].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick8left"),
                bricksTop+   helper.getConfigValue(con,"2brick8top"),
                bricksRight+ helper.getConfigValue(con,"2brick8right"),
                bricksBottom+helper.getConfigValue(con,"2brick8bottom"));

        b2[2].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick9left"),
                bricksTop+   helper.getConfigValue(con,"2brick9top"),
                bricksRight+ helper.getConfigValue(con,"2brick9right"),
                bricksBottom+helper.getConfigValue(con,"2brick9bottom"));
//////////////////////////////////////////////////////////////////////////////////////
        b1[1].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick2left"),
                bricksTop+   helper.getConfigValue(con,"2brick2top"),
                bricksRight+ helper.getConfigValue(con,"2brick2right"),
                bricksBottom+helper.getConfigValue(con,"2brick2bottom"));
        b1[2].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick3left"),
                bricksTop+   helper.getConfigValue(con,"2brick3top"),
                bricksRight+ helper.getConfigValue(con,"2brick3right"),
                bricksBottom+helper.getConfigValue(con,"2brick3bottom"));

        bricks4.setAll(bricksLeft+  helper.getConfigValue(con,"2brick4left"),
                bricksTop+   helper.getConfigValue(con,"2brick4top"),
                bricksRight+ helper.getConfigValue(con,"2brick4right"),
                bricksBottom+helper.getConfigValue(con,"2brick4bottom"));

        b1[3].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick5left"),
                bricksTop+   helper.getConfigValue(con,"2brick5top"),
                bricksRight+ helper.getConfigValue(con,"2brick5right"),
                bricksBottom+helper.getConfigValue(con,"2brick5bottom"));

        b1[4].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick6left"),
                bricksTop+   helper.getConfigValue(con,"2brick6top"),
                bricksRight+ helper.getConfigValue(con,"2brick6right"),
                bricksBottom+helper.getConfigValue(con,"2brick6bottom"));
/////////////////////////////////////////////////////////////////////////////////////////
        b2[3].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick11left"),
                bricksTop+   helper.getConfigValue(con,"2brick11top"),
                bricksRight+ helper.getConfigValue(con,"2brick11right"),
                bricksBottom+helper.getConfigValue(con,"2brick11bottom"));

        b2[4].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick12left"),
                bricksTop+   helper.getConfigValue(con,"2brick12top"),
                bricksRight+ helper.getConfigValue(con,"2brick12right"),
                bricksBottom+helper.getConfigValue(con,"2brick12bottom"));

        b2[5].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick13left"),
                bricksTop+   helper.getConfigValue(con,"2brick13top"),
                bricksRight+ helper.getConfigValue(con,"2brick13right"),
                bricksBottom+helper.getConfigValue(con,"2brick13bottom"));
//////////////////////////////////////////////////////////////////////////////////////////
        b1[5].setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick14left"),
                bricksTop+   helper.getConfigValue(con,"2brick14top"),
                bricksRight+ helper.getConfigValue(con,"2brick14right"),
                bricksBottom+helper.getConfigValue(con,"2brick14bottom")); */

        ///

        bricks10.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick10left"),
                bricksTop+   helper.getConfigValue(con,"2brick10top"),
                bricksRight+ helper.getConfigValue(con,"2brick10right"),
                bricksBottom+helper.getConfigValue(con,"2brick10bottom"));
        bricks10.setStatus(1);
        bricks10.getPaint().setColor(brick4color);
///////////////////////////////////////////////////////////////////////////////////////
        bricks7.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick7left"),
                bricksTop+   helper.getConfigValue(con,"2brick7top"),
                bricksRight+ helper.getConfigValue(con,"2brick7right"),
                bricksBottom+helper.getConfigValue(con,"2brick7bottom"));
        bricks7.setStatus(2);
        bricks7.getPaint().setColor(brick1color);

        bricks8.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick8left"),
                bricksTop+   helper.getConfigValue(con,"2brick8top"),
                bricksRight+ helper.getConfigValue(con,"2brick8right"),
                bricksBottom+helper.getConfigValue(con,"2brick8bottom"));
        bricks8.setStatus(1);
        bricks8.getPaint().setColor(brick4color);

        bricks9.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick9left"),
                bricksTop+   helper.getConfigValue(con,"2brick9top"),
                bricksRight+ helper.getConfigValue(con,"2brick9right"),
                bricksBottom+helper.getConfigValue(con,"2brick9bottom"));
        bricks9.setStatus(2);
        bricks9.getPaint().setColor(brick1color);
//////////////////////////////////////////////////////////////////////////////////////
        /*bricks2.setAllPos(bricks2.getLeft()+  helper.getConfigValue(con,"1brick2left"),
                bricks2.getTop()+   helper.getConfigValue(con,"1brick2top"),
                bricks2.getRight()+ helper.getConfigValue(con,"1brick2right"),
                bricks2.getBottom()+helper.getConfigValue(con,"1brick2bottom"));
        bricks2.setStatus(3);

        bricks3.setAllPos(bricks3.getLeft()+  helper.getConfigValue(con,"1brick3left"),
                bricks3.getTop()+   helper.getConfigValue(con,"1brick3top"),
                bricks3.getRight()+ helper.getConfigValue(con,"1brick3right"),
                bricks3.getBottom()+helper.getConfigValue(con,"1brick3bottom"));
        bricks3.setStatus(3);

        bricks4.setAll(bricks4.getLeft()+  helper.getConfigValue(con,"1brick4left"),
                bricks4.getTop()+   helper.getConfigValue(con,"1brick4top"),
                bricks4.getRight()+ helper.getConfigValue(con,"1brick4right"),
                bricks4.getBottom()+helper.getConfigValue(con,"1brick4bottom"));
        bricks4.setStatus(3);
        bricks5.setAllPos(bricks5.getLeft()+  helper.getConfigValue(con,"1brick5left"),
                bricks5.getTop()+   helper.getConfigValue(con,"1brick5top"),
                bricks5.getRight()+ helper.getConfigValue(con,"1brick5right"),
                bricks5.getBottom()+helper.getConfigValue(con,"1brick5bottom"));
        bricks5.setStatus(3);
        bricks6.setAllPos(bricks6.getLeft()+  helper.getConfigValue(con,"1brick6left"),
                bricks6.getTop()+   helper.getConfigValue(con,"1brick6top"),
                bricks6.getRight()+ helper.getConfigValue(con,"1brick6right"),
                bricks6.getBottom()+helper.getConfigValue(con,"1brick6bottom"));
        bricks6.setStatus(3);
/////////////////////////////////////////////////////////////////////////////////////////
        /*bricks11.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick11left"),
                bricksTop+   helper.getConfigValue(con,"2brick11top"),
                bricksRight+ helper.getConfigValue(con,"2brick11right"),
                bricksBottom+helper.getConfigValue(con,"2brick11bottom"));

        bricks12.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick12left"),
                bricksTop+   helper.getConfigValue(con,"2brick12top"),
                bricksRight+ helper.getConfigValue(con,"2brick12right"),
                bricksBottom+helper.getConfigValue(con,"2brick12bottom"));

        bricks13.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick13left"),
                bricksTop+   helper.getConfigValue(con,"2brick13top"),
                bricksRight+ helper.getConfigValue(con,"2brick13right"),
                bricksBottom+helper.getConfigValue(con,"2brick13bottom"));
//////////////////////////////////////////////////////////////////////////////////////////
        bricks14.setAllPos(bricksLeft+  helper.getConfigValue(con,"2brick14left"),
                bricksTop+   helper.getConfigValue(con,"2brick14top"),
                bricksRight+ helper.getConfigValue(con,"2brick14right"),
                bricksBottom+helper.getConfigValue(con,"2brick14bottom")); */
    }
//////////////////////////////////////////////// --- X --- ///////////////////////////////////////////////////////////////////////////

    public void gameOver(){
        /*for(int i=0;i<6; i++ )
        {
           b1[i].setAllPos(0,0,0,0);
            b2[i].setAllPos(0,0,0,0);
        }*/

        ///

        bricks4.setAll(0,0,0,0);


        bricks2.setAllPos(0,0,0,0);
        bricks3.setAllPos(0,0,0,0);

        bricks5.setAllPos(0,0,0,0);
        bricks6.setAllPos(0,0,0,0);
        bricks7.setAllPos(0,0,0,0);
        bricks8.setAllPos(0,0,0,0);
        bricks9.setAllPos(0,0,0,0);
        bricks10.setAllPos(0,0,0,0);
        bricks11.setAllPos(0,0,0,0);
        bricks12.setAllPos(0,0,0,0);
        bricks13.setAllPos(0,0,0,0);
        bricks14.setAllPos(0,0,0,0);
    }
}



