package com.hzy.customview.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author: wxj
 * @date: 2021/4/20
 * @description:
 */
public class squareImageView extends AppCompatImageView {
    public squareImageView(Context context) {
        super(context);
    }

    public squareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public squareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int size = Math.min(width, height);
        setMeasuredDimension(size, size);
    }
}
