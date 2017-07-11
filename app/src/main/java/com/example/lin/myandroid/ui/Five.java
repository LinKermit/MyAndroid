package com.example.lin.myandroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Five extends View {
    /*
    / 第一种
    public void drawBitmap (Bitmap bitmap, Matrix matrix, Paint paint)

    // 第二种
    public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)

    // 第三种
    public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
    public void drawBitmap (Bitmap bitmap, Rect src, RectF dst, Paint paint)
     */

    private Paint mPaint = new Paint();
    public Five(Context context) {
        super(context);
    }

    public Five(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint.setColor(Color.BLACK);       //设置画笔颜色
        mPaint.setStyle(Paint.Style.STROKE);  //设置画笔模式为填充
        mPaint.setAntiAlias(true);          //设置抗齿锯
    }

    public Five(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
////        canvas.drawBitmap(bitmap,new Matrix(),new Paint());//1
////        canvas.drawBitmap(bitmap,200,500,new Paint());//2
//
//        // 将画布坐标系移动到画布中央--3
//        canvas.translate(getWidth()/2,getHeight()/2);
//        // 指定图片绘制区域(左上角的四分之一)
//        Rect src = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight()/2);
//        // 指定图片在屏幕上显示的区域
//        Rect dst = new Rect(0,0,100,100);
//        // 绘制图片
//        canvas.drawBitmap(bitmap,src,dst,null);

        // 文本(要绘制的内容)
        String str = "ABCDEFG";
        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str,200,500,mPaint);
    }
}
