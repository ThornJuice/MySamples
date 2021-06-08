package com.hzy.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author: wxj
 * @date: 2021/4/20
 * @description:
 */
public class CircleView extends View {
    private Paint mPaint;
    private int radius = 300;
    public CircleView(Context context) {
        super(context);
        init();
    }


    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(radius,radius,radius,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = radius*2;
        int height = radius*2;
        width = resolveSize(width,widthMeasureSpec);
        height = resolveSize(height,heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
