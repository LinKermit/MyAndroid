package com.example.lin.myandroid.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Lin on 2017/6/8.
 * 绘制雷达图
 */

public class RadarView extends View {
    private int count = 6;
    //角度
    private float angle = (float) (Math.PI*2 /6);//60度

    //网格半径
    private float radius;

    //中心
    private int centerX;
    private int centerY;

    private String[] title = new String[]{"A","B","C","D","E","F"};

    public RadarView(Context context) {
        this(context,null);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);

    }
    Paint mPaint = new Paint();
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h,w)/2*0.8f;//半径取布局一半
        centerX = w/2;
        centerY = h/2;
        Log.e("onSizeChanged", "onSizeChanged:"+ w);
        postInvalidate();//在线程中更新界面

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     *
     */
    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        float r = radius/(count-1);//每个蛛丝之间的距离，总共有5段
        for (int i=0; i<count ;i++){
            float currentR = r*i; //每个蛛网的半径
            path.reset();
            for (int j=0 ;j<count ;j++){//计算每个蛛网顶点的位置，并连线
                if (j == 0){
                    path.moveTo(centerX+currentR , centerY);
                }else {
                    float x = (float) (centerX + currentR*Math.cos(angle*j));
                    float y = (float) (centerY + currentR*Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            path.close();
            canvas.drawPath(path,mPaint);
        }
    }

    /**
     * 绘制直线
     * 将path移动到最大蛛网的顶点，并与原点连线
     */
    private void drawLines(Canvas canvas){
        Path path = new Path();
        for (int j=0 ;j<count ;j++){
            float x = (float) (centerX + radius*Math.cos(angle*j));
            float y = (float) (centerY + radius*Math.sin(angle*j));
            path.moveTo(x,y);
            path.lineTo(centerX,centerY);
        }
        canvas.drawPath(path,mPaint);
    }

    /**
     * 待修改，文字的大小，绘制的其实位置
     * 绘制文本，给文本离蛛网增加点距离
     * 还要判断象限，考虑到x、y的负轴
     * @param canvas
     */
    private void drawText(Canvas canvas){
        for (int j=0 ;j<count ;j++){

            float x = (float) (centerX + radius*Math.cos(angle*j));
            float y = (float) (centerY + radius*Math.sin(angle*j));
            if (angle*j < Math.PI/2){//小于90度，在第4象限。数学坐标系
                canvas.drawText(title[j],x+20,y+20,mPaint);
            }else if (angle*j>Math.PI/2 && angle*j<Math.PI){//在第3象限
                canvas.drawText(title[j],x-20,y+20,mPaint);
            }
            else if (angle*j>Math.PI && angle*j<Math.PI*3/2){//在第2象限
                canvas.drawText(title[j],x-20,y-20,mPaint);
            }
            else if (angle*j>Math.PI*3/2 && angle*j<Math.PI*2){//在第1象限
                canvas.drawText(title[j],x+20,y-20,mPaint);
            }
        }

    }


    /**
     * 绘制覆盖的区域
     */
    private double[] data = new double[]{100,60,50,100,30,80};//覆盖区域的占比
    private double maxValue = 100;
    private void drawRegion(Canvas canvas){
        Path path = new Path();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i=0; i<count;i++){
            double percent = data[i]/maxValue;
            float x = (float) (centerX + radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY + radius*Math.sin(angle*i)*percent);

            if (i==0){
                path.moveTo(x,centerY);
            }else {
                path.lineTo(x,y);
            }
            canvas.drawCircle(x,y,5,mPaint);
        }
        mPaint.setAlpha(127);
        canvas.drawPath(path,mPaint);
    }
}
