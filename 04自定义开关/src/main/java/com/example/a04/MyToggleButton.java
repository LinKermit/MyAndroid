package com.example.a04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lin on 2017/1/23.
 *  一个视图从创建到显示过程中的主要方法
 * //1.构造方法实例化类
 * //2.测量-measure(int,int)-->onMeasure();
 * 如果当前View是一个ViewGroup,还有义务测量孩子
 * 孩子有建议权
 * //3.指定位置-layout()-->onLayout();
 * 指定控件的位置，一般View不用写这个方法，ViewGroup的时候才需要，一般View不需要重写该方法
 * //4.绘制视图--draw()-->onDraw(canvas)
 * 根据上面两个方法参数，进入绘制
 */

public class MyToggleButton extends View implements View.OnClickListener{
    private Bitmap backgroundBitmap; //初始化两个bitmap控件
    private Bitmap slidingBitmap;

    private int slideLeftMax;
    private Paint paint;
    private int slideLeft;


    private float startX;
    private float lastX;
    /**
     * 如果我们在布局文件使用该类，将会用这个构造方法实例该类，如果没有就崩溃
     * @param context
     * @param attrs
     */
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        backgroundBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
        slidingBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
        slideLeftMax = backgroundBitmap.getWidth() - slidingBitmap.getWidth();
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //视图的测量，显示控件的宽高
        setMeasuredDimension(backgroundBitmap.getWidth(),backgroundBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap,0,0,paint);
        canvas.drawBitmap(slidingBitmap,slideLeft,0,paint);
    }

    private boolean isOpen = false;
    private boolean isEnableClick = true;
    @Override
    public void onClick(View v) {
        isOpen = !isOpen;
       flushView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//执行父类的方法

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //1.记录按下的坐标
                lastX = startX = event.getX();
                isEnableClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                //2.计算结束值
                float endX = event.getX();
                //3.计算偏移量
                float distanceX = endX - startX;

//                slideLeft = (int) (slideLeft + distanceX);
                slideLeft += distanceX;
                if(slideLeft <0){
                    slideLeft = 0;
                }else if(slideLeft>slideLeftMax){
                    slideLeft = slideLeftMax;
                }
                //4.屏蔽非法值
                //5.刷新
                invalidate();

                //6.数据还原
                startX = event.getX();

                if(Math.abs(endX - lastX) > 5){
                    //滑动
                    isEnableClick = false;
                }
                break;
            case MotionEvent.ACTION_UP:

                if(!isEnableClick){
                    if(slideLeft > slideLeftMax/2){

                        //显示按钮开
                        isOpen = true;
                    }else{

                        isOpen = false;

                    }
                    flushView();
                }
                break;
        }
        return true;
    }

    private void flushView() {
        if(isOpen){
            slideLeft = slideLeftMax;
        }else{
            slideLeft = 0;
        }

        invalidate();//会导致onDraw()执行
    }

}
