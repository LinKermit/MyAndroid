package com.example.lin.myandroid.mViewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Lin on 2017/6/20.
 */

public class CustomView extends ViewGroup{


    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 宽度模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 测量宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        // 高度模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 测量高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 子view数目
        int childCount = getChildCount();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
