package com.example.lin.myandroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/15.
 *
 * Canvas之画布操作
 * ⑴位移(translate)---translate是坐标系的移动--坐标系的移动
 * (2)缩放----缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴
 * public void scale (float sx, float sy)
   public final void scale (float sx, float sy, float px, float py)//自定义缩放中心

    ⑶旋转(rotate)
   public void rotate (float degrees)
   public final void rotate (float degrees, float px, float py)

   ⑷错切(skew)
   public void skew (float sx, float sy)
  float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
  float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
 */

public class Third extends View {

    Paint mPaint = new Paint();
    public Third(Context context) {
        this(context,null);
    }

    public Third(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setStrokeWidth(10f);         //设置画笔宽度为10px
        mPaint.setAntiAlias(true);          //设置抗齿锯

    }

    public Third(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mWidth =getWidth();
        int mHeight = getHeight();

        // 位移 -- 在坐标原点绘制一个蓝色圆形
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);//坐标系先移动
//        canvas.drawCircle(0,0,100,mPaint);


        // 缩放 -- 将坐标系原点移动到画布正中心
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//        canvas.scale(0.8f,0.5f);                // 画布缩放-x轴 y轴  当缩放比例为负数的时候会根据缩放中心轴进行翻转
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);


        // 旋转 -- 将坐标系原点移动到画布正中心
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        RectF rect = new RectF(0,-400,400,0);   // 矩形区域
//        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
//        canvas.drawRect(rect,mPaint);
//        canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rect,mPaint);

        // 错切将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0,0,200,200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);                       // 水平错切 <- 45度

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }
}
