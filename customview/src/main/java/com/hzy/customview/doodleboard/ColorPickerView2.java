package com.hzy.customview.doodleboard;

import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class ColorPickerView2 extends View {
    private Paint mPaint;//渐变色环画笔
    private Paint mCenterPaint;//中间圆画笔
    private Paint mLinePaint;//分隔线画笔
    private Paint mRectPaint;//渐变方块画笔

    private Shader rectShader;//渐变方块渐变图像
    private float rectLeft;//渐变方块左x坐标
    private float rectTop;//渐变方块右x坐标
    private float rectRight;//渐变方块上y坐标
    private float rectBottom;//渐变方块下y坐标

    private int[] mCircleColors;//渐变色环颜色
    private int[] mRectColors;//渐变方块颜色

    private int mHeight;//View高
    private int mWidth;//View宽
    private float r;//色环半径(paint中部)
    private float centerRadius;//中心圆半径

    private boolean downInCircle = true;//按在渐变环上
    private boolean downInRect;//按在渐变方块上
    private boolean highlightCenter;//高亮
    private boolean highlightCenterLittle;//微亮
    private RectF mRectF = new RectF();
    private OnSelectedColorListener mOnSelectedColorListener;

    public ColorPickerView2(Context context, int initColor, int height, int width, OnSelectedColorListener listener) {
        super(context);
        this.mHeight = height;
        this.mWidth = width;
        setMinimumHeight(height);
        setMinimumWidth(width);
        init(context,width);
    }

    private void init(Context context,int width) {

        mCircleColors = new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF,
                0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000};
        float[] position = new float[7];
        position[0] = 0.1f;
        position[1] = 0.2f;
        position[2] = 0.3f;
        position[3] = 0.4f;
        position[4] = 0.8f;
        position[5] = 0.9f;
        position[6] = 1f;
        Shader s = new SweepGradient(0, 0, mCircleColors, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(s);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dp2px(context, 30));
        r = width / 2 * 0.7f - mPaint.getStrokeWidth() * 0.5f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,r,mPaint);
        canvas.restore();

    }

    public interface OnSelectedColorListener {
        void onSelected(int color);
    }

    public int dp2px(Context context, float dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5F);
    }
}