package com.example.lin.myandroid.circle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lin.myandroid.R;
import com.example.lin.myandroid.demo.MagicCircle;

public class MagicCircleActivity extends AppCompatActivity {

    MagicCircle circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_circle);
//        circle = (MagicCircle) findViewById(R.id.circle);
//        circle.startAnimation();
    }
}
