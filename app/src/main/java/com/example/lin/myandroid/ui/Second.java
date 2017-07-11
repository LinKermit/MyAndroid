package com.example.lin.myandroid.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.lin.myandroid.R;

/**
 * Created by Administrator on 2017/4/15.
 *
 * 画笔
 * mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
 * STROKE                //描边
   FILL                  //填充
   FILL_AND_STROKE       //描边加填充
 */

public class Second extends View {
    private Paint mPaint = new Paint();
    public Second(Context context) {
        this(context,null);
    }

    public Second(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);          //设置抗齿锯
    }

    public Second(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "早上好";
//        canvas.drawColor(Color.BLUE);//绘制背景颜色
//        canvas.drawText(text,100,100,mPaint);//绘制文本

        //绘制文本
//        Resources resources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher);
//        canvas.drawBitmap(bitmap,getWidth()/2,getHeight()/2,mPaint);
        //绘制点
//        canvas.drawPoint(200,200,mPaint);
//        canvas.drawPoints(new float[]{
//                500,500,
//                500,600,
//                500,700
//        },mPaint);

        //绘制直线
//        canvas.drawLine(0,0,200,200,mPaint);
//        canvas.drawLines(new float[]{300,400,600,700},mPaint);

        //绘制矩形
//        Rect rect = new Rect(100,300,200,800);//矩形左上角和右下角两个点的坐标
//        canvas.drawRect(rect,mPaint);
//        mPaint.setColor(Color.GRAY);
//        RectF rectF = new RectF(100,100,600,400);
//        canvas.drawRoundRect(rectF,60,30,mPaint);//矩形圆角- 30画30厘米长的圆弧
//
//        // 绘制圆角矩形
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRoundRect(rectF,250,150,mPaint);

        //绘制椭圆
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawOval(rectF,mPaint);
//        canvas.drawOval(100,100,800,400,mPaint);
//
//        //绘制圆
//        canvas.drawCircle(500,500,400,mPaint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。


//        // 绘制圆弧 startAngle-开始角度 sweepAngle-扫过角度
//        RectF rectF2 = new RectF(0,400,300,500);
//        // 绘制背景矩形
//        mPaint.setColor(Color.GRAY);
//        canvas.drawRect(rectF2,mPaint);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawArc(rectF2,0,90,true,mPaint);//true为圆弧里面是否绘制


        //绘制饼状图
        mPaint.setColor(Color.GREEN);
        RectF rectF1 = new RectF(0,400,300,700);
        canvas.drawArc(rectF1,0,90,true,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawArc(rectF1,90,30,true,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF1,120,120,true,mPaint);


    }
}
