package com.example.lin.myandroid.besizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Lin on 2017/6/9.
 * 三阶贝塞尔曲线练习，需要两个控制点
 */

public class Bezier2 extends View {
    private Paint mPaint;
    private float centerX,centerY;
    private PointF start,end,control1,control2;

    //传入一个控制点
    private boolean mode = true;
    public void setMode(boolean mode) {
        this.mode = mode;
    }


    public Bezier2(Context context) {
        this(context,null);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;
        start = new PointF(centerX-200,centerY);
        end = new PointF(centerX+200,centerY);
        control1 = new PointF(centerX,centerY-100);
        control2 = new PointF(centerX,centerY+100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode){
            control1.x = event.getX();
            control1.y = event.getY();
        }else {
            control2.x = event.getX();
            control2.y = event.getY();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(control1.x, control1.y, mPaint);
        canvas.drawPoint(control2.x, control2.y, mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
        canvas.drawLine(control1.x, control1.y,control2.x, control2.y, mPaint);
        canvas.drawLine(control2.x, control2.y,end.x, end.y, mPaint);


        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x,control2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }
}
