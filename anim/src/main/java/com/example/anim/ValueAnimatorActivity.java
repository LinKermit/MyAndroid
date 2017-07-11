package com.example.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ValueAnimatorActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        textView = (TextView) findViewById(R.id.tv_anim);

        //平滑的动画过渡
        ValueAnimator anim = ValueAnimator.ofFloat(0f,1f);//将值从0过渡到1的动画
        // .ofFloat(0f,5f,3f,10f) 可以支持多个参数。
        // .ofInt(0,100)
        // .ofObject()
        anim.setDuration(500);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {//动画的监听器执行过程
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();//获取当前值
            }
        });
        anim.setStartDelay(2000);//设置延迟播放
        anim.setRepeatCount(3);
        anim.setRepeatMode(ValueAnimator.RESTART);//RESTART和REVERSE两种，分别表示重新播放和倒序播放
        anim.start();


        /** ObjectAnimator -- "alpha","rotation","translationX","translationY","scaleX","scaleY"
         *
         *   AnimatorSet play
         *   after(Animator anim)   将现有动画插入到传入的动画之后执行
             after(long delay)   将现有动画延迟指定毫秒后执行
             before(Animator anim)   将现有动画插入到传入的动画之前执行
             with(Animator anim)   将现有动画和传入的动画同时执行
         */
        //组合AnimatorSet
        ObjectAnimator rotate = ObjectAnimator.ofFloat(textView,"rotation",0f,360f);
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(textView,"translationX",-500f,0f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();


        //Animator监听器
        anim.addListener(new Animator.AnimatorListener() {//监听动画的各种事件
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.addListener(new AnimatorListenerAdapter() {//可以只添加动画的某一阶段
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

        /**
         * 通过xml文件实现动画AnimatorInflater
         *
             Animator animator = AnimatorInflater.loadAnimator(context, R.animator.anim_file);
             animator.setTarget(view);
             animator.start();
         */

        /**
         TypeEvaluator



         */

    }


}
