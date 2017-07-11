package com.example.lin.myandroid.circle;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Lin on 2017/6/13.
 */

public class MagicCircle2 extends View {

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private float radius = 50;//圆的半径
    private float mDifference;
    private Paint mPaint;
    private Path mPath;

    private VPoint p2,p4;
    private HPoint p1,p3;

    private ValueAnimator animator;

    public MagicCircle2(Context context) {
        this(context,null);
    }

    public MagicCircle2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MagicCircle2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAnimator();
        animator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        maxLength = w - radius -radius;
        cDistance = mDifference*0.45f;
    }
    private float maxLength;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        canvas.translate(radius,radius);
        if(mInterpolatedTime>=0&&mInterpolatedTime<=0.2){
            model1(mInterpolatedTime);
        }
        else if(mInterpolatedTime>0.2&&mInterpolatedTime<=0.5){
            model2(mInterpolatedTime);
        }else if(mInterpolatedTime>0.5&&mInterpolatedTime<=0.8){
            model3(mInterpolatedTime);
        }else if(mInterpolatedTime>0.8&&mInterpolatedTime<=0.9){
            model4(mInterpolatedTime);
        }else if(mInterpolatedTime>0.9&&mInterpolatedTime<=1){
            model5(mInterpolatedTime);
        }

        float offset = maxLength*(mInterpolatedTime-0.2f);
        offset = offset>0?offset:0;
        p1.adjustAllX(offset);
        p2.adjustAllX(offset);
        p3.adjustAllX(offset);
        p4.adjustAllX(offset);

        mPath.moveTo(p1.x,p1.y);
        mPath.cubicTo(p1.right.x, p1.right.y, p2.bottom.x, p2.bottom.y, p2.x,p2.y);
        mPath.cubicTo(p2.top.x, p2.top.y, p3.right.x, p3.right.y, p3.x,p3.y);
        mPath.cubicTo(p3.left.x, p3.left.y, p4.top.x, p4.top.y, p4.x,p4.y);
        mPath.cubicTo(p4.bottom.x,p4.bottom.y,p1.left.x,p1.left.y,p1.x,p1.y);

        canvas.drawPath(mPath,mPaint);

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xFFfe626d);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mDifference = radius*C;//画圆的偏移量

        p2 = new VPoint();
        p4 = new VPoint();

        p1 = new HPoint();
        p3 = new HPoint();
    }
    class VPoint{
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void setX(float x){
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset){
            top.y -= offset;
            bottom.y += offset;
        }

        public void adjustAllX(float offset){
            this.x+= offset;
            top.x+= offset;
            bottom.x+=offset;
        }
    }

    class HPoint{
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void setY(float y){
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustAllX(float offset){
            this.x +=offset;
            left.x +=offset;
            right.x +=offset;
        }
    }

    private void model0(){
        p1.setY(radius);
        p3.setY(-radius);
        p3.x = p1.x = 0;
        p3.left.x =  p1.left.x = -mDifference;
        p3.right.x = p1.right.x = mDifference;

        p2.setX(radius);
        p4.setX(-radius);
        p2.y = p4.y = 0;
        p2.top.y =  p4.top.y = -mDifference;
        p2.bottom.y = p4.bottom.y = mDifference;
    }

    private void model1(float time){//0~0.2
        model0();

        p2.setX(radius+radius*time*5);
    }
    private float cDistance;
    private void model2(float time){//0.2~0.5
        model1(0.2f);
        time = (time - 0.2f) * (10f / 3);
        p1.adjustAllX(radius/2 * time );
        p3.adjustAllX(radius/2 * time );
        p2.adjustY(cDistance * time);
        p4.adjustY(cDistance * time);
    }

    private void model3(float time){//0.5~0.8
        model2(0.5f);
        time = (time - 0.5f) * (10f / 3);
        p1.adjustAllX(radius / 2 * time);
        p3.adjustAllX(radius / 2 * time);
        p2.adjustY(-cDistance * time);
        p4.adjustY(-cDistance * time);

        p4.adjustAllX(radius / 2 * time);

    }

    private void model4(float time){//0.8~0.9
        model3(0.8f);
        time = (time - 0.8f) * 10;
        p4.adjustAllX(radius / 2 * time);
    }

    private void model5(float time){
        model4(0.9f);
        time = time - 0.9f;
        p4.adjustAllX((float) (Math.sin(Math.PI*time*10f)*(2/10f*radius)));
    }



    private float mInterpolatedTime;
    private void initAnimator(){
        animator = ValueAnimator.ofFloat(0,1).setDuration(3000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mInterpolatedTime = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }


}
