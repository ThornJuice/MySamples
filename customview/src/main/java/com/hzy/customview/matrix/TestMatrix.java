package com.hzy.customview.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hzy.customview.BitmapUtil;
import com.hzy.customview.DensityUtil;
import com.hzy.customview.R;

/**
 * @author: wxj
 * @date: 2021/5/31
 * @description:
 */
public class TestMatrix extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmapMatrix;
    public static final int imageWidth = DensityUtil.dip2px(200);
    public TestMatrix(Context context) {
        super(context);
        init(context);
    }

    public TestMatrix(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    void init(Context context) {
        mBitmapMatrix = BitmapUtil.createBitmap(context.getResources(), R.drawable.thelittleprince2, imageWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        Matrix matrix = new Matrix();

        int bw = mBitmapMatrix.getWidth();
        int bh = mBitmapMatrix.getHeight();

        float[] dst = new float[8];
        float[] src = new float[8];

        /**
         * 原始坐标
         */
        src[0] = 0;
        src[1] = 0;
        src[2] = bw;
        src[3] = 0;
        src[4] = 0;
        src[5] = bh;
        src[6] = bw;
        src[7] = bh;

        dst[0] = 0;
        dst[1] = 0;
        dst[2] = bw;
        dst[3] = 0;
        dst[4] = 0;
        dst[5] = bh;

        //最后一个点的坐标xy
        dst[6] = bw * 1;
        dst[7] = bh * 2;
        matrix.setScale(0.5f, 0.5f);
        matrix.setPolyToPoly(src, 0, dst, 0, 4);


        canvas.drawBitmap(mBitmapMatrix, matrix, mPaint);

    }
}
