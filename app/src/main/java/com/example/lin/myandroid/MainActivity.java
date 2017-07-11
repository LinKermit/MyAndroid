package com.example.lin.myandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.example.lin.myandroid.demo.CustomProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomProgressBar = (CustomProgressBar) findViewById(R.id.customBar);
        mHint = (TextView) findViewById(R.id.tv_hint);
        startAmin();
    }

    CustomProgressBar mCustomProgressBar;
    TextView mHint;
    private void startAmin()
    {

        ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(mCustomProgressBar, "percentage", 0,0.9f);
        mObjectAnimator.setDuration(2000);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.addListener(new AnimatorListenerAdapter()
        {

            @Override
            public void onAnimationEnd(Animator animation)
            {
                //动画结束后 显示分数
                try
                {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(mHint, "alpha", 0f, 1f);
                    animator.setDuration(1000);
                    animator.setInterpolator(new LinearInterpolator());
                    animator.start();
                    mHint.setVisibility(View.VISIBLE);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                super.onAnimationEnd(animation);
            }
        });
        mObjectAnimator.start();
    }

}
