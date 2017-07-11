package com.example.lin.myandroid.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lin.myandroid.R;
import com.example.lin.myandroid.practice.CustomProgressBar2;

public class PracticeActivity extends AppCompatActivity {

    private CustomProgressBar2 customBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        customBar2 = (CustomProgressBar2) findViewById(R.id.customBar2);
        customBar2.setDegress(90);
        customBar2.initAnimator();
    }
}
