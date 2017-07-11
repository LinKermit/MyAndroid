package com.example.lin.myandroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Four extends View {

    public Four(Context context) {
        this(context,null);
    }
    public Four(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        recording();    // 调用录制
    }

    public Four(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPicture(mPicture,new RectF(0,0,500,250));

//        // 包装成为Drawable
//        PictureDrawable drawable = new PictureDrawable(mPicture);
//        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
//        drawable.setBounds(0,0,500,mPicture.getHeight());
//        drawable.draw(canvas);
    }
    // 1.创建Picture
    private Picture mPicture = new Picture();
    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,250,paint);
        mPicture.endRecording();
    }

    /**
     * 1.canvas.drawBitmap(bitmap,new Matrix(),new Paint());
     * 2.canvas.drawBitmap(bitmap,200,500,new Paint()); --与坐标原点的距离
     *
     */
}
