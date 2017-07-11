package com.example.anim;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Drawable动画
//        imageView = (ImageView) findViewById(R.id.iv_anim);
//        AnimationDrawable ad = (AnimationDrawable) imageView.getBackground();
//        ad.start();
    }
}
