package com.example.lin.myandroid.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lin on 2017/6/12.
 * 自己练习绘制5星雷达
 */

public class RadarView2 extends View{

    private Paint mPaint;
    private float currentX;
    private float currentY;
    private float radius;
    private int count = 5;//5份
    private float angle = (float) (Math.PI*2/5);//分成5份，每个角度
    public RadarView2(Context context) {
        this(context,null);
    }

    public RadarView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadarView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = Math.min(h,w)/2*0.8f;//半径取布局一半
        currentX = w/2;
        currentY = h/2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(currentX,currentY);
        canvas.scale(1,-1);
        drawPolygon(canvas);
    }

    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        for (int j=0; j<5; j++){//
            float currentR = radius/4*j;
            path.reset();

            for (int i=0; i<count; i++){
                if (i==0){
                    path.moveTo(0,currentR);
                }else {
                    float x = (float) (Math.sin(angle*i)*currentR);
                    float y = (float) (Math.cos(angle*i)*currentR);
                    path.lineTo(x,y);
                }
            }
            path.close();
            canvas.drawPath(path,mPaint);
        }

    }
}
