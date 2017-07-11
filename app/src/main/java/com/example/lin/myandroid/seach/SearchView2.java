package com.example.lin.myandroid.seach;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lin on 2017/6/12.
 * 自定义搜索控件
 * PathMeasure  getPosTan() getSegment()
 * 通过ValueAnimator控制平滑动画，结束监听和过程监听，写成一个变量
 * 用枚举定义4种状态 和当前状态
 * 画圆弧的RectF
 */

public class SearchView2 extends View {

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private Paint mPaint;
    private float currentX;
    private float currentY;
    private Path searchPath;
    private Path circlePath;
    private PathMeasure mMeasure;

    //用枚举型定义4中状态
    private enum State{
        NONE,
        START,
        SEARCH,
        END
    }
    private State currentState = State.NONE;

    //定义三种动画
    private ValueAnimator startAnimator;
    private ValueAnimator searchAnimator;
    private ValueAnimator endAnimator;

    ValueAnimator.AnimatorUpdateListener updateListener;
    AnimatorListenerAdapter listenerAdapter;

    public SearchView2(Context context) {
        this(context,null);
    }

    public SearchView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initPath();

        initListener();
        initAnimator();
        currentState = State.START;
        startAnimator.start();
    }



    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        searchPath = new Path();
        circlePath = new Path();
        mMeasure = new PathMeasure();
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
        canvas.scale(1,-1);
        canvas.drawColor(Color.parseColor("#0082D7"));

//        canvas.drawPath(searchPath,mPaint);
        drawSearch(canvas);

    }

    private void initPath() {
        //画外圆
        RectF rectF = new RectF(-100,-100,100,100);//圆弧的坐标一定是--++
        circlePath.addArc(rectF,-45f,359.9f);

        //画内圆
        RectF rectF2 = new RectF(-50,-50,50,50);//圆弧的坐标一定是--++
        searchPath.addArc(rectF2,-45f,359.9f);

        mMeasure.setPath(circlePath,false);
        float[] pos = new float[2];
        mMeasure.getPosTan(0,pos,null);//获取到外部圆的起始点坐标

        searchPath.lineTo(pos[0],pos[1]);//画出search路径。
    }

    private void drawSearch(Canvas canvas) {

        switch (currentState){
            case NONE:
                break;
            case START:
                Path dst = new Path();
                mMeasure.setPath(searchPath,false);
                mMeasure.getSegment(mMeasure.getLength()*startValue,mMeasure.getLength(),dst,true);
                canvas.drawPath(dst,mPaint);
                break;
            case SEARCH:
                Path dst2 = new Path();
                mMeasure.setPath(circlePath,false);
                float stop = mMeasure.getLength() * startValue;
                float start = (float) (stop - ((0.5 - Math.abs(startValue - 0.5)) * 200f));
                mMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            case END:
                Path dst3 = new Path();
                mMeasure.setPath(searchPath,false);
                mMeasure.getSegment(mMeasure.getLength()*startValue,mMeasure.getLength(),dst3,true);
                canvas.drawPath(dst3,mPaint);

        }

    }


    private float startValue;//将3000毫秒在（0-1）中
    private void initAnimator() {
        startAnimator = ValueAnimator.ofFloat(0f,1f).setDuration(3000);
        searchAnimator = ValueAnimator.ofFloat(0f,1f).setDuration(3000);
        endAnimator = ValueAnimator.ofFloat(1f,0f).setDuration(3000);
        //当动画结束时，发出一个消息
        startAnimator.addUpdateListener(updateListener);
        searchAnimator.addUpdateListener(updateListener);
        endAnimator.addUpdateListener(updateListener);

        startAnimator.addListener(listenerAdapter);
        searchAnimator.addListener(listenerAdapter);
        endAnimator.addListener(listenerAdapter);
    }

    private void initListener() {
        updateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        };

        listenerAdapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                handler.sendEmptyMessage(0);
            }
        };
    }
    boolean isOver = false;
    Handler handler = new Handler(){
        //最初为NONE，进入动画是为START，第一次发送消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (currentState){
                case START://第一次发送消息是为START，进入搜索状态
                    currentState = State.SEARCH;
                    startAnimator.removeAllListeners();
                    searchAnimator.start();
                    break;
                case SEARCH:
                    if (!isOver){
                        searchAnimator.start();
                        isOver = true;
                    }else {
                        currentState = State.END;
                        searchAnimator.removeAllListeners();
                        endAnimator.start();
                    }

                    break;
                case END:
                    currentState = State.NONE;
                    break;
            }
        }
    };

}
