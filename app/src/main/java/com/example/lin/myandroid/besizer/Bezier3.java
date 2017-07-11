package com.example.lin.myandroid.besizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Lin on 2017/6/9.
 * 绘制一个爱心
 * 首先通过贝塞尔曲线画一个圆，top和right为固定点，需要知道两个控制点。控制的x坐标为C乘半径。
 */

public class Bezier3 extends View {
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private Paint mPaint;

    private float centerX,centerY;
    private int radius = 200;
    private float mDifference = radius*C;        // 圆形的控制点与数据点的差值

    private float[] mData = new float[8];//4个点,上下左右
    /**
     * 定义控制点
     */
    private float[] control = new float[16];
    //开始动态变化
    private float mDuration = 1000;                     // 变化总时长
    private float mCurrent = 0;                         // 当前已进行时长
    private float mCount = 100;                         // 将时长总共划分多少份
    private float mPiece = mDuration/mCount;            // 每一份的时长


    public Bezier3(Context context) {
        this(context,null);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);


        // 初始化数据点
        mData[0] = 0;
        mData[1] = radius;

        mData[2] = radius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -radius;

        mData[6] = -radius;
        mData[7] = 0;
        

        // 初始化控制点
        control[0]  = mData[0]+mDifference;
        control[1]  = mData[1];

        control[2]  = mData[2];
        control[3]  = mData[3]+mDifference;

        control[4]  = mData[2];
        control[5]  = mData[3]-mDifference;

        control[6]  = mData[4]+mDifference;
        control[7]  = mData[5];

        control[8]  = mData[4]-mDifference;
        control[9]  = mData[5];

        control[10] = mData[6];
        control[11] = mData[7]-mDifference;

        control[12] = mData[6];
        control[13] = mData[7]+mDifference;

        control[14] = mData[0]-mDifference;
        control[15] = mData[1];
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(centerX,centerY);//将画布移至中点
        canvas.scale(1,-1);//翻转

        Path path = new Path();
        path.moveTo(mData[0],mData[1]);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        
        path.cubicTo(control[0],  control[1],  control[2],  control[3],     mData[2], mData[3]);
        path.cubicTo(control[4],  control[5],  control[6],  control[7],     mData[4], mData[5]);
        path.cubicTo(control[8],  control[9],  control[10], control[11],    mData[6], mData[7]);
        path.cubicTo(control[12], control[13], control[14], control[15],    mData[0], mData[1]);

        canvas.drawPath(path, mPaint);//绘制一个圆

        mCurrent += mPiece;
        if (mCurrent < mDuration){
            mData[1] -= 120/mCount;
            control[7] += 80/mCount;
            control[9] += 80/mCount;

            control[4] -= 20/mCount;
            control[10] += 20/mCount;

            postInvalidateDelayed((long) mPiece);
        }
    }
}
