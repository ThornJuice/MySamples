package com.hzy.baselib.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 等宽的 imgview
 */
public class AequilateImgView extends AppCompatImageView {
    public AequilateImgView(Context context) {
        super(context);
    }

    public AequilateImgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AequilateImgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
