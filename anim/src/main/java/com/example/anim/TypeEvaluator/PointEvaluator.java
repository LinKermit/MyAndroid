package com.example.anim.TypeEvaluator;

import android.animation.TypeEvaluator;

/**
 * Created by Lin on 2017/6/12.
 * ValueAnimator中还有一个ofObject()方法，是用于对任意对象进行动画操作的
 * 系统将完全无法知道如何从初始对象过度到结束对象，因此这个时候我们就需要实现一个自己的TypeEvaluator来告知系统如何进行过度
 *
 * FloatEvaluator中
 * float startFloat = ((Number) startValue).floatValue();
 return startFloat + fraction * (((Number) endValue).floatValue() - startFloat);
 *
 */

public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;

        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());//计算两个点的差值
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
