package com.code.mdwasifurrahman.calc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button  btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btn0,btn00,
            btnDot,btnpls,btnmin,btnMul,btnDiv,btnEq,btnHist,btnClr, btnAC,
            btnMem,btnMemP,btnMemM,btnMemC;

    TextView display;

    String leftOperand = "N" , Operator ="", rightOperand = "N" ,Tot_op=""; String PreOperator ="N",res="0";

    Boolean Dotoops= false,  LorR = false; // "Left operand or Right operand" flag

    Double  r=0.0 ;

    String filename = "History_Log";
    String text="";
    FileOutputStream outputStream;

    SharedPreferences pref;
    SharedPreferences.Editor editor;


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public FileOutputStream  Write_In_Log(String fileContents)
    {
        try
        {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return  outputStream;
    }

    //////////////////////////////////////////////////////////////////

    public String Read_The_Log()
    {

        try
        {
            FileInputStream fs = openFileInput(filename);
            int size = fs.available();
            byte[] buffer = new byte[size];
            fs.read(buffer);
            fs.close();
            text = new String(buffer);

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return  text;
    }

    /////////////////////////////////////////////////////////////////////////

    public void DigitButtonTrigger(String s)
    {

        display = findViewById(R.id.textView2);

        if (LorR == false) {

            if (leftOperand == "N") {
                leftOperand = s;

                if (PreOperator == "N") {
                    display.setText(leftOperand);
                } else {
                    display.setText(PreOperator + leftOperand);
                }
            }else
            {
                if(leftOperand.equals("0.")|| leftOperand.equals("0.0") || leftOperand.equals("0.00")|| leftOperand.equals("0.000"))
                {
                    leftOperand += s;
                    if(PreOperator=="N"){
                        display.setText(leftOperand);}
                    else {display.setText(PreOperator+leftOperand);}
                }else {
                    if (Double.parseDouble(leftOperand) == 0) {

                        leftOperand = s;
                        if (PreOperator == "N") {
                            display.setText(leftOperand);
                        } else {
                            display.setText(PreOperator + leftOperand);
                        }
                    } else {
                        leftOperand += s;
                        if (PreOperator == "N") {
                            display.setText(leftOperand);
                        } else {
                            display.setText(PreOperator + leftOperand);
                        }
                    }
                }
            }
        }
        else
        {
            if (rightOperand == "N") {
                rightOperand = s;
                if(PreOperator=="N"){
                    display.setText(leftOperand + Operator + rightOperand);}
                else{display.setText(PreOperator+leftOperand + Operator + rightOperand);}
            }
            else {
                if (rightOperand.equals("0.") || rightOperand.equals("0.0") || rightOperand.equals("0.00")|| leftOperand.equals("0.00")) {
                    rightOperand += s;
                    if (PreOperator == "N") {
                        display.setText(leftOperand + Operator + rightOperand);
                    } else {
                        display.setText(PreOperator + leftOperand + Operator + rightOperand);
                    }
                } else {
                    if (Double.parseDouble(rightOperand) == 0) {
                        rightOperand = s;
                        if (PreOperator == "N") {
                            display.setText(leftOperand + Operator + rightOperand);
                        } else {
                            display.setText(PreOperator + leftOperand + Operator + rightOperand);
                        }
                    } else {
                        rightOperand += s;
                        if (PreOperator == "N") {
                            display.setText(leftOperand + Operator + rightOperand);
                        } else {
                            display.setText(PreOperator + leftOperand + Operator + rightOperand);
                        }
                    }
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Pre_Operation(String sn)
    {
        display = findViewById(R.id.textView2);

        if (PreOperator == "N")    // 0...
        {
            if (Operator.equals("+")) {

                res = Double.toString(Double.parseDouble(leftOperand) + Double.parseDouble(rightOperand));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;   // previous operation

                Write_In_Log( Tot_op);



                // After pushing Tot_op >> History Log  //////

                leftOperand = res;      // getting ready for performing new operation
                Operator = sn;   // operator update
                rightOperand="N";
                display.setText(leftOperand + Operator);
                LorR = true;
            } else if (Operator.equals("-")) {
                res = Double.toString(Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;


                Write_In_Log( Tot_op);

                // After pushing Tot_op >> History Log  //////
                if (Double.parseDouble(res)  < 0) {
                    PreOperator = "-";
                    leftOperand = Double.toString((-1)*Double.parseDouble(res));
                    display.setText(PreOperator+leftOperand + Operator);

                } else {
                    leftOperand = res;
                    display.setText(leftOperand + Operator);
                }

                rightOperand="N";
                Operator = sn;      // operator update

                LorR = true;
            } else if (Operator.equals("*")) {
                res = Double.toString(Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;


                Write_In_Log( Tot_op);

                // After pushing Tot_op History Log  //////

                leftOperand = res;
                rightOperand="N";
                Operator = sn;      // operator update
                display.setText(leftOperand + Operator);
                LorR = true;
            } else if (Operator.equals("/")) {

                if(Double.parseDouble(rightOperand)== 0 )
                {
                    Toast.makeText(getApplicationContext(), "Divisor can't be Zero!", Toast.LENGTH_LONG).show();

                }else{

                res = Double.toString(Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;


                    Write_In_Log( Tot_op);

                // After pushing Tot_op History Log  //////

                leftOperand = res;
                rightOperand="N";
                Operator = sn;      // operator update
                display.setText(leftOperand + Operator);
                LorR = true;}
            } else {
                display.setText("");
            }
        }else      // 1111
            {

            if (Operator.equals("+")) {
                res = Double.toString(Double.parseDouble(rightOperand) - Double.parseDouble(leftOperand));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;    // previous operation

                Write_In_Log( Tot_op);

                // After pushing Tot_op >> History Log  //////
                if (Double.parseDouble(rightOperand) - Double.parseDouble(leftOperand) < 0) {

                    leftOperand = Double.toString((-1)*Double.parseDouble(res));
                    display.setText(PreOperator+leftOperand + Operator);
                } else {
                    PreOperator = "N";
                    leftOperand = res;
                    display.setText(leftOperand + Operator);

                }

                Operator = sn;   // operator update
                rightOperand = "N";

                LorR = true;
            } else if (Operator.equals("-")) {
                res = Double.toString(-Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                Write_In_Log( Tot_op);

                // After pushing Tot_op >> History Log  //////

                leftOperand = Double.toString((-1)*Double.parseDouble(res));
                rightOperand = "N";
                Operator = sn;      // operator update
                display.setText(PreOperator+leftOperand + Operator);
                LorR = true;
            } else if (Operator.equals("*")) {
                res = Double.toString(-(Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand)));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                Write_In_Log( Tot_op);

                // After pushing Tot_op History Log  //////

                leftOperand = Double.toString((-1)*Double.parseDouble(res));
                rightOperand = "N";
                Operator = sn;      // operator update
                display.setText(PreOperator+leftOperand + Operator);
                LorR = true;
            } else if (Operator.equals("/")) {

                if(Double.parseDouble(rightOperand)== 0 )
                {
                    Toast.makeText(getApplicationContext(), "Divisor can't be Zero!", Toast.LENGTH_LONG).show();
                }else{

                res = Double.toString(-(Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand)));

                Tot_op = " "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                    Write_In_Log(Tot_op);

                // After pushing Tot_op History Log  //////

                    leftOperand = Double.toString((-1)*Double.parseDouble(res));
                    rightOperand = "N";
                Operator = sn;      // operator update
                display.setText(PreOperator+leftOperand + Operator);
                LorR = true;
               }
            } else {
                display.setText("");
            }

        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public void syntaxMin(String neg)
    {
        display = findViewById(R.id.textView2);

        if (PreOperator=="N"&& leftOperand == "N" && Operator == "" && rightOperand == "N")   // 0000
        {
            PreOperator=neg;
            display.setText(PreOperator);
        }
        else if(PreOperator!="N"&& leftOperand == "N" && Operator == "" && rightOperand == "N")   // 1000
        {
            display.setText(PreOperator);
        }
        else if(PreOperator!="N"&& leftOperand != "N" && Operator == "" && rightOperand == "N")    // 1100
        {
            Operator=neg;
            display.setText(PreOperator+leftOperand+Operator);
            LorR=true;
        }
        else if(PreOperator!="N"&& leftOperand != "N" && Operator != "" && rightOperand == "N")     //   1110 ; replace op
        {
            Operator=neg;
            display.setText(PreOperator+leftOperand+Operator);

        }
        else if(PreOperator=="N"&& leftOperand != "N" && Operator == "" && rightOperand == "N")    // 0100
        {
            Operator=neg;
            display.setText(leftOperand+Operator);
            LorR=true;
        }
        else if(PreOperator=="N"&& leftOperand != "N" && Operator != "" && rightOperand == "N")    // 0110  ; rep op
        {
            Operator=neg;
            display.setText(leftOperand+Operator);
        }
        else if(PreOperator=="N"&& leftOperand != "N" && Operator != "" && rightOperand != "N")  //  0111
        {
            Pre_Operation(neg);
        }
        else if(PreOperator!="N"&& leftOperand != "N" && Operator != "" && rightOperand != "N")  //  1111
        {
            Pre_Operation(neg);
        }else
        {
            display.setText("");
        }


    }

    ///////////////////////////////////////////////////////////////////////////////////

    public void syntax(String sng) // except (-)
    {
        display = findViewById(R.id.textView2);

        if (PreOperator == "N")  // 0...
        {

            if (leftOperand != "N" && Operator == "" && rightOperand == "N")   // 0100   ok
            {
            Operator = sng;

            display.setText(leftOperand + Operator);

            LorR = true;
            } /*else if (leftOperand == "N" && Operator == "" && rightOperand == "N") // 0000   X
            {
                display.setText(" ");
            } else if (leftOperand == "N" && Operator != "" && rightOperand == "N") // 0010   X
            {
                display.setText(" ");
                Operator = "";
            } else if (leftOperand == "N" && Operator == "" && rightOperand != "N") // 0001   X
            {
                rightOperand = "N";
                display.setText(" ");
            } else if (leftOperand == "N" && Operator != "" && rightOperand != "N") //  0011   X
            {
                rightOperand = "N";
                Operator = "";
                display.setText(" ");
            } else if (leftOperand != "N" && Operator == "" && rightOperand != "N") //  0101     X
            {
                rightOperand = "N";
                leftOperand = "N";
                display.setText(" ");
            }*/ else if (leftOperand != "N" && Operator != "" && rightOperand == "N") // 0110    ok
            {

            Operator = sng;
            display.setText(leftOperand + Operator);
            }
            else if (leftOperand != "N" && Operator != "" && rightOperand != "N")  // 0111 ok
            {
                Pre_Operation(sng);
            }
            else {
                  display.setText(" ");
               }

    }
    else {
            if (leftOperand != "N" && Operator == "" && rightOperand == "N")   // 1100   ok
            {
                Operator = sng;

                display.setText(PreOperator + leftOperand + Operator);

                LorR = true;
            }
            else if (leftOperand != "N" && Operator != "" && rightOperand == "N") // 1110    ok
            {

                Operator = sng;
                display.setText(PreOperator +leftOperand + Operator);
            } else if (leftOperand != "N" && Operator != "" && rightOperand != "N")  // 1111 ok
            {
                Pre_Operation(sng);
            } else {
                display.setText(" ");
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        btn0 = (Button)findViewById(R.id.button_id0);
        btn1 = (Button)findViewById(R.id.button_id1);
        btn2 = (Button)findViewById(R.id.button_id2);
        btn3 = (Button)findViewById(R.id.button_id3);
        btn4 = (Button)findViewById(R.id.button_id4);
        btn5 = (Button)findViewById(R.id.button_id5);
        btn6 = (Button)findViewById(R.id.button_id6);
        btn7 = (Button)findViewById(R.id.button_id7);
        btn8 = (Button)findViewById(R.id.button_id8);
        btn9 = (Button)findViewById(R.id.button_id9);
        btn00 = (Button)findViewById(R.id.button_id00);

        btnDot = (Button)findViewById(R.id.button_iddot);
        btnpls = (Button)findViewById(R.id.button_idplus);
        btnmin = (Button)findViewById(R.id.button_idminus);
        btnMul = (Button)findViewById(R.id.button_idmult);
        btnDiv = (Button)findViewById(R.id.button_iddiv);

        btnEq = (Button)findViewById(R.id.button_ide);
        btnHist = (Button)findViewById(R.id.button_idHist);
        btnClr = (Button)findViewById(R.id.button_idClr);
        btnAC =   (Button)findViewById(R.id.button_idAC);

        btnMem =   (Button)findViewById(R.id.button_idM);
        btnMemP =   (Button)findViewById(R.id.button_idMP);
        btnMemM =   (Button)findViewById(R.id.button_idMm);
        btnMemC =   (Button)findViewById(R.id.button_idMC);

        pref = getApplicationContext().getSharedPreferences("M", Context.MODE_PRIVATE);
        editor = pref.edit();

        // setting On click actions:

        btnMem.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {
                display = findViewById(R.id.textView2);

                if(LorR==false)
                {
                    if(leftOperand!="N")
                    {
                        editor.putString("M",leftOperand) ;
                        editor.apply();
                    }
                }

                display.setText("M: " + pref.getString("M",""));
                leftOperand="N";

            }
        });

        btnMemP.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {
                display = findViewById(R.id.textView2);

                if(LorR==false) {
                    if (leftOperand != "N") {

                        if (PreOperator == "N") {

                            r = Double.parseDouble(pref.getString("M", "")) + Double.parseDouble(leftOperand);
                            Tot_op = " " + pref.getString("M", "") + " + " + leftOperand + " = " + Double.toString(r) + "                                 ";

                            Write_In_Log(Tot_op);

                            // After pushing Tot_op History Log  //////

                            editor.putString("M", Double.toString(r));
                            editor.apply();

                            leftOperand = "N";
                            Toast.makeText(getApplicationContext(), " Added in memory", Toast.LENGTH_LONG).show();

                        } else {
                            r = Double.parseDouble(pref.getString("M", "")) - Double.parseDouble(leftOperand);

                            Tot_op = " " + pref.getString("M", "") + " - " + leftOperand + " = " + Double.toString(r) + "                                 ";

                            Write_In_Log(Tot_op);

                            // After pushing Tot_op History Log  //////
                            editor.putString("M", Double.toString(r));
                            editor.apply();

                            leftOperand = "N";
                            PreOperator = "N";
                            Toast.makeText(getApplicationContext(), " Added in memory", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        display.setText("Insert a Value");
                    }
                }
            }
        });

        btnMemM.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {
                display = findViewById(R.id.textView2);

                if(LorR==false) {
                    if (leftOperand != "N") {

                        if (PreOperator == "N") {
                            r = Double.parseDouble(pref.getString("M", "")) - Double.parseDouble(leftOperand);
                            Tot_op = " " + pref.getString("M", "") + " - " + leftOperand + " = " + Double.toString(r) + "                                 ";

                            Write_In_Log(Tot_op);

                            // After pushing Tot_op History Log  //////

                            editor.putString("M", Double.toString(r));
                            editor.apply();

                            leftOperand = "N";
                            Toast.makeText(getApplicationContext(), " Added in memory", Toast.LENGTH_LONG).show();

                        } else {
                            r = Double.parseDouble(pref.getString("M", "")) + Double.parseDouble(leftOperand);

                            Tot_op = " " + pref.getString("M", "") + " + " + leftOperand + " = " + Double.toString(r) + "                                 ";

                            Write_In_Log(Tot_op);

                            // After pushing Tot_op History Log  //////

                            editor.putString("M", Double.toString(r));
                            editor.apply();

                            leftOperand = "N";
                            PreOperator = "N";
                            Toast.makeText(getApplicationContext(), " Added in memory", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        display.setText("Insert a Value");
                    }
                }
            }
        });



        btnMemC.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {
                display = findViewById(R.id.textView2);

                editor.putString("M","0") ;
                editor.apply();

                display.setText( "M: "+ pref.getString("M",""));
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s0 =  btn0.getText().toString();
                DigitButtonTrigger(s0);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s1 =  btn1.getText().toString();
                DigitButtonTrigger(s1);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s2 =  btn2.getText().toString();
                DigitButtonTrigger(s2);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s3 =  btn3.getText().toString();
                DigitButtonTrigger(s3);
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s4 =  btn4.getText().toString();
                DigitButtonTrigger(s4);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s5 =  btn5.getText().toString();
                DigitButtonTrigger(s5);
            }
        });


        btn6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s6 =  btn6.getText().toString();
                DigitButtonTrigger(s6);
            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s7 = btn7.getText().toString();
                DigitButtonTrigger(s7);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {

            public void onClick(View btn) {

                String s8 = btn8.getText().toString();

                DigitButtonTrigger(s8);
            }
        });


        btn9.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                        String s9 = btn9.getText().toString();

                        DigitButtonTrigger(s9);
                    }
                });


                btnDot.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {
                        display = findViewById(R.id.textView2);
                        String sD = btnDot.getText().toString();


                        if (LorR == false) {
                            for (int i = 0; i < leftOperand.length(); i++) {
                                if (leftOperand.charAt(i) == '.') {
                                    Dotoops = true;
                                }
                            }

                            if (Dotoops == true) {
                                Toast.makeText(getApplicationContext(), " Multiple dot not allowed !!", Toast.LENGTH_LONG).show();

                                if (PreOperator == "N") {
                                    display.setText(leftOperand);
                                } else {
                                    display.setText(PreOperator + leftOperand);
                                }
                                Dotoops = false;
                            } else {
                                if(leftOperand=="N")
                                {
                                    leftOperand="0";
                                    leftOperand += sD;
                                }else{
                                leftOperand += sD;}
                                if (PreOperator == "N") {
                                    display.setText(leftOperand);
                                } else {
                                    display.setText(PreOperator + leftOperand);
                                }
                            }

                        } else {
                            for (int i = 0; i < rightOperand.length(); i++) {
                                if (rightOperand.charAt(i) == '.') {
                                    Dotoops = true;
                                }
                            }

                            if (Dotoops == true) {
                                Toast.makeText(getApplicationContext(), " Multiple dot not allowed !!", Toast.LENGTH_LONG).show();

                                if (PreOperator == "N") {
                                    display.setText(leftOperand+ Operator+ rightOperand);
                                } else {
                                    display.setText(PreOperator + leftOperand + Operator+ rightOperand);
                                }
                                Dotoops = false;
                            }else
                                {
                                if(rightOperand=="N")
                                {
                                    rightOperand="0";
                                    rightOperand += sD;
                                }else{
                                    rightOperand += sD;}

                                if (PreOperator == "N") {
                                    display.setText(leftOperand+ Operator+ rightOperand);
                                } else {
                                    display.setText(PreOperator + leftOperand + Operator+ rightOperand);
                                }

                            }
                        }


                    }
                });


                btnpls.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                        String sP = btnpls.getText().toString();

                        syntax(sP);
                    }
                });


                btnmin.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                        String sm = btnmin.getText().toString();
                        syntaxMin(sm);
                    }
                });


                btnMul.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                        String sM = btnMul.getText().toString();
                        syntax(sM);
                    }
                });


                btnDiv.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                        String sD = btnDiv.getText().toString();
                        syntax(sD);
                    }
                });

                // .....................................................................................................
                btnEq.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                        String sE = btnEq.getText().toString();

                        display = findViewById(R.id.textView2);

                        if (leftOperand != "N" && Operator != "N" && rightOperand != "N")   /// .111
                        {
                            if (PreOperator == "N")    /// 0111
                            {
                                if (Operator.equals("+"))
                                {
                                    res = Double.toString(Double.parseDouble(leftOperand) + Double.parseDouble(rightOperand));

                                    Tot_op = " "+leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log(Tot_op);

                                    // After pushing Tot_op >> History Log  //////

                                    leftOperand = res;
                                    rightOperand = "N";
                                    Operator = "";
                                    display.setText(leftOperand);
                                    LorR = false;
                                } else if (Operator.equals("-")) {
                                    res = Double.toString(Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand));

                                    Tot_op =" "+ leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log( Tot_op);

                                    // After pushing Tot_op >> History Log  //////
                                    if(Double.parseDouble(res)<0)
                                    {
                                        PreOperator="-";
                                        leftOperand = Double.toString((-1)*Double.parseDouble(res));
                                        display.setText(PreOperator+leftOperand);
                                    }
                                    else{
                                        leftOperand = res;
                                        display.setText(leftOperand); }

                                    rightOperand = "N";
                                    Operator = "";

                                    LorR = false;
                                } else if (Operator.equals("*")) {
                                    res = Double.toString(Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand));

                                    Tot_op =" " +leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log( Tot_op);

                                    // After pushing Tot_op >> History Log  //////

                                    leftOperand =res ;
                                    rightOperand = "N";
                                    Operator = "";
                                    display.setText(leftOperand);
                                    LorR = false;
                                } else if (Operator.equals("/")) {
                                    res = Double.toString(Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand));

                                    Tot_op =" " +leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log(Tot_op);

                                    // After pushing Tot_op >> History Log  //////

                                    leftOperand =res ;
                                    rightOperand = "N";
                                    Operator = "";
                                    display.setText(leftOperand);
                                    LorR = false;
                                } else {

                                }
                            } else {     // 1...
                                if (Operator.equals("+")) {
                                    res = Double.toString(Double.parseDouble(rightOperand) - Double.parseDouble(leftOperand));

                                    Tot_op = " " +leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;   // previous operation

                                    Write_In_Log(Tot_op);

                                    // After pushing Tot_op >> History Log  //////

                                    if(Double.parseDouble(res)<0)
                                    {
                                        leftOperand = Double.toString((-1)*Double.parseDouble(res));
                                        display.setText(PreOperator+leftOperand);
                                    }
                                    else{
                                        PreOperator="N";
                                        leftOperand = res;
                                        display.setText(leftOperand); }

                                    rightOperand = "N";
                                    Operator = "";

                                    LorR = false;
                                } else if (Operator.equals("-")) {
                                    res = Double.toString(-Double.parseDouble(leftOperand) - Double.parseDouble(rightOperand));

                                    Tot_op = " " +leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log(Tot_op);

                                    // After pushing Tot_op >> History Log  //////

                                    leftOperand = Double.toString((-1)*Double.parseDouble(res));
                                    display.setText(PreOperator+leftOperand);
                                    rightOperand = "N";
                                    Operator = "";     // operator update

                                    LorR = false;
                                } else if (Operator.equals("*")) {
                                    res = Double.toString(-(Double.parseDouble(leftOperand) * Double.parseDouble(rightOperand)));

                                    Tot_op = " " +leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log( Tot_op);

                                    // After pushing Tot_op History Log  //////

                                    leftOperand = Double.toString((-1)*Double.parseDouble(res));
                                    display.setText(PreOperator+leftOperand);
                                    rightOperand = "N";
                                    Operator = "";     // operator update

                                    LorR = false;
                                } else if (Operator.equals("/")) {
                                    res = Double.toString(-(Double.parseDouble(leftOperand) / Double.parseDouble(rightOperand)));

                                    Tot_op =" " +leftOperand+ "  " + Operator + "  " + rightOperand + "  =  " + res + "                          " ;

                                    Write_In_Log(Tot_op);

                                    // After pushing Tot_op History Log  //////

                                    leftOperand = Double.toString((-1)*Double.parseDouble(res));
                                    display.setText(PreOperator+leftOperand);
                                    rightOperand = "N";
                                    Operator = "";     // operator update

                                    LorR = false;
                                } else {

                                }
                            }
                        }


                    }
                });
                // .............................................................................................................

                btnHist.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {

                     Intent i = new Intent(btn.getContext(), LOG.class).putExtra("data", filename);
                     startActivity(i);
                     //finish();
                    }
                });

                // -------------------------------------------------------------------------------------------------------------------

                btn00.setOnClickListener(new View.OnClickListener()
                {

                    public void onClick(View btn) {

                        String s00 = btn0.getText().toString();
                        DigitButtonTrigger(s00+s00);
                    }
                });


                btnClr.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {


                        display = findViewById(R.id.textView2);



                        if (LorR == false) {

                            if (leftOperand.length() > 1) {
                                leftOperand = leftOperand.substring(0, leftOperand.length() - 1);
                                if (PreOperator == "N") {
                                    display.setText(leftOperand);
                                } else {
                                    display.setText(PreOperator + leftOperand);
                                }
                            }
                            else {
                                if ( leftOperand != "N")
                                {
                                    leftOperand = "N";
                                    if (PreOperator == "N") {
                                        display.setText(" ");
                                    } else {
                                        display.setText(PreOperator);
                                    }
                                }else
                                {
                                    if (PreOperator == "N") {
                                        display.setText(" ");
                                    } else {
                                        PreOperator = "N";
                                        display.setText("");
                                    }
                                }
                            }


                        } else {
                            if (rightOperand == "N") {
                                Operator = "";
                                LorR = false;

                                if (PreOperator == "N") {
                                    display.setText(leftOperand);
                                } else {
                                    display.setText(PreOperator + leftOperand);
                                }
                            } else {
                                if (rightOperand.length() > 1) {
                                    rightOperand = rightOperand.substring(0, rightOperand.length() - 1);
                                    if (PreOperator == "N") {
                                        display.setText(leftOperand + Operator + rightOperand);
                                    } else {
                                        display.setText(PreOperator + leftOperand + Operator + rightOperand);
                                    }
                                } else {
                                    rightOperand = "N";

                                    if (PreOperator == "N") {
                                        display.setText(leftOperand + Operator);
                                    } else {
                                        display.setText(PreOperator + leftOperand + Operator);
                                    }

                                }
                            }
                        }

                        if(PreOperator=="N"&& leftOperand == "N" && Operator == "" && rightOperand == "N") // 0000
                        { display.setText(" ");}





                    }
                });


                btnAC.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View btn) {


                        display = findViewById(R.id.textView2);

                        PreOperator = "N";
                        leftOperand = "N";
                        Operator = "";
                        rightOperand = "N";

                        LorR= false;
                        display.setText(" ");
                }
            });

        }
    }