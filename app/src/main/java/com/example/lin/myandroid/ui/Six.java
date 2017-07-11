package com.example.lin.myandroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Six extends View {

    Paint mPaint =new Paint();
    int mWidth;
    int mHeight;
    public Six(Context context) {
        super(context);
    }

    public Six(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public Six(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();

//        canvas.translate(mWidth/2,mHeight/2);//移动到坐标原点
//        Path path = new Path();
//        path.lineTo(200,200);
////        path.moveTo(200,100);
//        path.setLastPoint(200,100); 	//设置之前操作的最后一个点位置
//        path.lineTo(200,0);
//        path.close();//close方法用于连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形。
//                     //如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。
//        canvas.drawPath(path,mPaint);


        //第2组: addXxx与arcTo   -- Path.Direction - CW-顺时针和CCW-逆时针

//        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        Path path = new Path();
//        path.addRect(-200,-200,200,200, Path.Direction.CW);
//        path.setLastPoint(-300,300);
//        canvas.drawPath(path,mPaint);

        //第二类(Path)
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        Path path2 = new Path();
        path.addRect(-200,-200,200,200, Path.Direction.CW);
        path2.addCircle(0,100,100, Path.Direction.CW);
//        path.addPath(path2,0,200);
        mPaint.setColor(Color.BLACK);           // 绘制合并后的路径
        canvas.drawPath(path,mPaint);
        canvas.drawPath(path2,mPaint);

    }
}
