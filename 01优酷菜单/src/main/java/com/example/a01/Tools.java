package com.example.a01;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

/**
 * Created by lin on 2017/1/20.
 * 显示和隐藏level
 */
public class Tools {
    public static void hideView(ViewGroup view) {
      hideView(view,0);
    }

    public static void showView(ViewGroup view) {
        showView(view,0);
    }

    public static void hideView(ViewGroup view, int startTimeMillis) {
//        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());//旋转动画
//        ra.setDuration(500);//播放时间500ms
//        ra.setFillAfter(true);//设置播放完成后看不见
//        ra.setStartOffset(startTimeMillis);
//        view.startAnimation(ra);//设置动画开始
//
//        /*解决旋  for (int i=0; i<view.getChildCount();i++){
//            View children = view.getChildAt(i);
//            children.setEnabled(false);转图片后，按钮还可点击
//        设置viewgroup中按钮不可用 相对布局中的控件
//         */
//
//        }
        //属性动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0,180);
        animator.setDuration(500);
        animator.setStartDelay(startTimeMillis);
        animator.start();

        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());

    }

    public static void showView(ViewGroup view, int startTimeMillis) {
//        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());//旋转动画
//        ra.setDuration(500);//播放时间500ms
//        ra.setFillAfter(true);//设置播放完成后看不见
//        ra.setStartOffset(startTimeMillis);
//        view.startAnimation(ra);//设置动画开始
//        for (int i=0; i<view.getChildCount();i++){
//            View children = view.getChildAt(i);
//            children.setEnabled(false);
//        }

        //属性动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",180,360);
        animator.setDuration(500);
        animator.setStartDelay(startTimeMillis);
        animator.start();
        //设置旋转的中心
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }
}
