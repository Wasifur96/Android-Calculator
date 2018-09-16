package com.code.mdwasifurrahman.calc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;

public class LOG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        String File = intent.getStringExtra("data");

        String Sub="",Text = "";
        try
        {
            FileInputStream fs = openFileInput(File);
            int size = fs.available();
            byte[] buffer = new byte[size];
            fs.read(buffer);
            fs.close();
            Text = new String(buffer);

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        TextView textview = findViewById(R.id.textViewLOG);


                textview.setText(Text);

    }
}
