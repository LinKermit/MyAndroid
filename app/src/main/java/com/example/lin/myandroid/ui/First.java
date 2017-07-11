package com.example.lin.myandroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/4/15.
 *
 * 第一章
  1、坐标系，屏幕坐标系。在默认的屏幕坐标系中角度增大方向为顺时针。
  2、View的坐标系统是相对于父控件而言的.
     getTop();       //获取子View左上角距父View顶部的距离
     getLeft();      //获取子View左上角距父View左侧的距离
     getBottom();    //获取子View右下角距父View顶部的距离
     getRight();     //获取子View右下角距父View左侧的距离
  3、MotionEvent中 get 和 getRaw 的区别
     event.getX();       //触摸点相对于其所在组件坐标系的坐标
     event.getY();

     event.getRawX();    //触摸点相对于屏幕默认坐标系的坐标
     event.getRawY();

    第二章
     角度 = 2πr/360
     弧度 rad = deg x π / 180
     deg = rad x 180 / π

    第四章  view分两类
  1.自定义ViewGroup：自定义ViewGroup一般是利用现有的组件根据特定的布局方式来组成新的组件，大多继承自ViewGroup或各种Layout，包含有子View。
  2.自定义View：在没有现成的View，需要自己实现的时候，就使用自定义View，一般继承自View，SurfaceView或其他的View，不包含子View。

  3、测量View大小(onMeasure)
    如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec); 要调用 setMeasuredDimension( widthsize, heightsize);
  4、确定子View布局位置(onLayout)
    onLayout一般是循环取出子View，然后经过计算得出各个子View位置的坐标值
    child.layout(l,t,r,b)--view距离父view左，上，右，底的距离
  5、绘制内容(onDraw)
  6.对外提供操作方法和监听回调
 */

public class First extends View {
    private int width;
    private int height;
    public First(Context context) {
        this(context,null);
    }

    public First(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }//view的初始化

    public First(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int itemWidth;
    private int itemHeight;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//测量view的大小
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();//绘制单个字母的宽度

        int widthsize = MeasureSpec.getSize(widthMeasureSpec);//取出宽度的确切数值
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);//取出宽度的测量模式
        if (itemWidth == widthsize){
            Log.e("TAG", "onMeasure: "+widthsize );
        }else {
            Log.e("TAG", "itemWidth: "+itemWidth );
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//确定view的大小
        super.onSizeChanged(w, h, oldw, oldh);//宽度(w), 高度(h)，这两个参数就是View最终的大小。
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {//实际绘制内容
        super.onDraw(canvas);

        invalidate();//强制重绘
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {//确定view的布局（包含子view时使用）
        super.onLayout(changed, left, top, right, bottom);
    }
}
