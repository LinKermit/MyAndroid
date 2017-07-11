package com.example.lin.myandroid.practice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by Lin on 2017/6/13.
 */

public class CustomProgressBar2 extends View {

    private Paint mPaint;
    private float currentX;
    private float currentY;

    private int mRadius0 = 87;
    private int mRadius1 = 97;

    private int mRadius = 113;

    private int mRadius2 = 130;
    private int mRadius3 = 150;

    private RectF mOval = new RectF();
    private RectF mOval2 = new RectF();
    private RectF mOval3 = new RectF();

    private float mCurrentAngle = 270;
    private float mStartAngle = 135;
    private float mEndAngle = 270;
    private ValueAnimator animator;
    private float percent;

    public CustomProgressBar2(Context context) {
        this(context,null);
    }

    public CustomProgressBar2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
//        initAnimator();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentX = w/2;
        currentY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(currentX,currentY);

        mOval = new RectF(-mRadius,-mRadius,mRadius,mRadius);
        mOval2 = new RectF(-mRadius2,-mRadius2,mRadius2,mRadius2);
        mOval3 = new RectF(-mRadius3,-mRadius3,mRadius3,mRadius3);

        mPaint.setStrokeWidth(2);
        mPaint.setAlpha(26);
        canvas.drawArc(mOval3, mStartAngle, mEndAngle, false, mPaint);

        mPaint.setAlpha(51);
        canvas.drawArc(mOval2, mStartAngle, mEndAngle, false, mPaint);

        //画中心圆
        mPaint.setAlpha(153);
        drawLines(canvas);


        //进度条底色
        mPaint.setStrokeWidth(20);
        mPaint.setAlpha(102);
        canvas.drawArc(mOval, mStartAngle, mEndAngle, false, mPaint);

        mPaint.setAlpha(255);
        canvas.drawArc(mOval, mStartAngle, mCurrentAngle*percent, false, mPaint);
    }

    private void drawLines(Canvas canvas) {

        for (int i=0;i<360;i = i+3) {//分成120分
            double d = 2 * Math.PI * i / 360;
            float x = (float) (Math.sin(d)*mRadius0);
            float y = (float) (Math.cos(d)*mRadius0);
            float x1 = (float) (Math.sin(d)*mRadius1);
            float y1 = (float) (Math.cos(d)*mRadius1);
            canvas.drawLine(x,y,x1,y1,mPaint);
        }
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
    }

    public void setDegress(float degress) {
        this.degress = degress;
    }

    private float degress = 100;
    public void initAnimator(){


        animator = ValueAnimator.ofFloat(0,degress/100).setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }


//    ObjectAnimator animator1 = ObjectAnimator.ofFloat(this,"percentage",0,1);
//    public void setPercentage(float percent)
//    {
//        // mCurrentAngle = mStartAngle + percent * (mEndAngle - mStartAngle);
//        mCurrentAngle = percent * mEndAngle;
//        invalidate();
//    }
}
