package com.example.lin.myandroid.besizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.lin.myandroid.R;

public class BesizerActivity extends AppCompatActivity {

    RadioButton rb1;
    RadioButton rb2;
    Bezier2 bezier2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besizer);
//        bezier2 = (Bezier2) findViewById(R.id.bezier);
//        rb1 = (RadioButton) findViewById(R.id.rb_mode1);
//        rb2 = (RadioButton) findViewById(R.id.rb_mode2);
//
//        rb1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bezier2.setMode(true);
//                rb1.setChecked(true);
//                rb2.setChecked(false);
//
//            }
//        });
//
//        rb2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bezier2.setMode(false);
//                rb1.setChecked(false);
//                rb2.setChecked(true);
//
//            }
//        });
    }

}
