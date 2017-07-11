package com.example.lin.myandroid.seach;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lin on 2017/6/9.
 * 暂时做了第一部分的动画
 * 通过path画圆弧，addArc(RectF oval, float startAngle, float sweepAngle)
 * 通过外部圆的起点画把手。mMeasure.getPosTan(0，pos，tan),获取起点坐标
 * 通过search，来实现消失的效果。getSegment(截取的长度，总长，是否连线)
 */

public class SearchView extends View {

    private float currentX;
    private float currentY;

    private Paint mPaint;
    // 放大镜与外部圆环
    private Path path_srarch;
    private Path path_circle;

    PathMeasure mMeasure;

    // 动效过程监听器
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private float mAnimatorValue = 0;//动画的数值，从1-0，来截取长度
    // 默认的动效周期 2s
    private int defaultDuration = 5000;
    // 控制各个过程的动画
    private ValueAnimator mStartingAnimator;


    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPath();
        initListener();
        initAnimator();
        mStartingAnimator.start();
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
        canvas.translate(currentX,currentY);//将画布移到View的中心点
        canvas.drawColor(Color.parseColor("#0082D7"));

//        canvas.drawPath(path_srarch,mPaint);

        mMeasure.setPath(path_srarch,false);
        Path dst = new Path();
        mMeasure.getSegment(mMeasure.getLength()*mAnimatorValue,mMeasure.getLength(),dst,true);
        canvas.drawPath(dst,mPaint);
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }
    private void initPath() {
        path_srarch = new Path();
        path_circle = new Path();

        RectF oval1 = new RectF(-50,-50,50,50);
        path_srarch.addArc(oval1,45,359.9f);

        RectF oval2 = new RectF(-100,-100,100,100);
        path_circle.addArc(oval2,45,359.9f);

        mMeasure = new PathMeasure();
        mMeasure.setPath(path_circle,false);// 放大镜把手的位置 外部圆环的起点。通过getPosTan得到起点的坐标

        float[] pos = new float[2];
        mMeasure.getPosTan(0,pos,null);

        path_srarch.lineTo(pos[0],pos[1]);//内部圆环画完圈 在连线。及把手
    }

    private void initListener(){
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };
    }

    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);

        mStartingAnimator.addUpdateListener(mUpdateListener);


    }
}
