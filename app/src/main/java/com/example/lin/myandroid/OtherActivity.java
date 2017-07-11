package com.example.lin.myandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lin.myandroid.besizer.BesizerActivity;
import com.example.lin.myandroid.circle.MagicCircleActivity;
import com.example.lin.myandroid.interfaces.MeClientActivity;
import com.example.lin.myandroid.mViewgroup.ViewGroupActivity;

public class OtherActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt1,bt2,bt3,bt4,bt5,bt6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                Intent intent1 = new Intent(this, BesizerActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt2:
                Intent intent2 = new Intent(this, MagicCircleActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt3:
                Intent intent3 = new Intent(this, MeClientActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt4:
                Intent intent4 = new Intent(this, ViewGroupActivity.class);
                startActivity(intent4);
                break;
            case R.id.bt5:
                Intent intent5 = new Intent(this, MainActivity.class);
                startActivity(intent5);
                break;
            case R.id.bt6:
                Intent intent6 = new Intent(this, BesizerActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
