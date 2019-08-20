package com.hzy.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static com.hzy.myapplication2.DensityUtil.dip2px;

public class ProgressView extends View {
    Context context;
    Paint paint;
    FormXCJCBean bean;

    public ProgressView(Context context, FormXCJCBean bean) {
        super(context);
        this.context = context;
        this.bean = bean;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();//获取画笔
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(dip2px(context, 14));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int top = getTop();       //top相对于父控件的y轴坐标x
        int left = getLeft();     //left相对于父控件的x轴坐标
        int bottom = getBottom(); //bottom相对于父控件的y轴坐标
        int right = getRight();   //right相对于父控件的x轴坐标
        int height = bottom - top;  //本view的高度
        int width = right - left;   //本view的宽度
        int pWidth = dip2px(context, 2);   //竖线的宽度 2dp
        int pHeight = dip2px(context, 20);  //竖线的高度20dp
        int mHeight = dip2px(context, 8);  //进度条的高度8dp
        int totalWidth = width - pWidth * 2;   //进度条的总宽度204-4 =200dp
        float width0 = getDivide(bean.yzg, bean.total) * totalWidth;   //第一条线的宽度
        float width1 = getDivide(bean.dfy, bean.total) * totalWidth;   //第二条线的宽度
        float width2 = getDivide(bean.dzg, bean.total) * totalWidth;   //第三条线的宽度
        float width3 = getDivide(bean.fzcgb, bean.total) * totalWidth;  //第四条线的宽度

        //第一条线
        RectF rectF0 = new RectF(pWidth, height / 2 - mHeight / 2, width0 + pWidth, height / 2 + mHeight / 2);
        paint.setColor(ContextCompat.getColor(context, R.color.green_light));
        canvas.drawRect(rectF0, paint);
        //第二条线
        RectF rectF1 = new RectF(width0 + pWidth, height / 2 - mHeight / 2, width0 + width1 + pWidth, height / 2 + mHeight / 2);
        paint.setColor(ContextCompat.getColor(context, R.color.green_deep));
        canvas.drawRect(rectF1, paint);
        //第三条线
        RectF rectF2 = new RectF(width0 + width1 + pWidth, height / 2 - mHeight / 2, width0 + width1 + width2 + pWidth, height / 2 + mHeight / 2);
        paint.setColor(ContextCompat.getColor(context, R.color.orange));
        canvas.drawRect(rectF2, paint);
        //第四条线
        RectF rectF3 = new RectF(width0 + width1 + width2 + pWidth, height / 2 - mHeight / 2, totalWidth, height / 2 + mHeight / 2);
        paint.setColor(ContextCompat.getColor(context, R.color.gray));
        canvas.drawRect(rectF3, paint);
        //画首部半圆
        int radius = mHeight / 2;//半径为4dp
        RectF radiusrectF0 = new RectF(0, height / 2 - mHeight / 2, radius * 2, height / 2 + mHeight / 2);
        if (width0 == 0) {
            paint.setColor(ContextCompat.getColor(context, R.color.green_deep));
            if (width1 == 0) {
                paint.setColor(ContextCompat.getColor(context, R.color.orange));
                if (width2 == 0) {
                    paint.setColor(ContextCompat.getColor(context, R.color.gray));
                }
            }
        } else {
            paint.setColor(ContextCompat.getColor(context, R.color.green_light));
        }
        canvas.drawArc(radiusrectF0, 90, 180, true, paint);
        //画尾部半圆
        RectF radiusrectF1 = new RectF(width - radius * 2, height / 2 - mHeight / 2, width, height / 2 + mHeight / 2);
        if (width3 == 0) {
            paint.setColor(ContextCompat.getColor(context, R.color.orange));
            if (width2 == 0) {
                paint.setColor(ContextCompat.getColor(context, R.color.green_deep));
                if (width1 == 0) {
                    paint.setColor(ContextCompat.getColor(context, R.color.green_light));
                }
            }
        } else {
            paint.setColor(ContextCompat.getColor(context, R.color.gray));
        }
        canvas.drawArc(radiusrectF1, 270, 180, true, paint);
        //竖线
        RectF pRectF = new RectF(width0 + width1, height / 2 - pHeight / 2, width0 + width1 + pWidth, height / 2 + pHeight / 2);
        paint.setColor(ContextCompat.getColor(context, R.color.green_deep));
        canvas.drawRect(pRectF, paint);

        //文字
        paint.setColor(ContextCompat.getColor(context, R.color.text_black));
        canvas.drawText("60%已整改", width0 + width1, height / 2 - mHeight * 2, paint);
    }

    /**
     * 计算百分比
     */
    private String getPercent(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal divide = b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP);
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(divide.doubleValue());
        return result;
    }

    /**
     * 除法
     */
    private float getDivide(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal divide = b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP);
        return divide.floatValue();
    }
}
