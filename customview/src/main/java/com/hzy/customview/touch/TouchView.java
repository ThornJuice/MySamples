package com.hzy.customview.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author: wxj
 * @date: 2021/4/25
 * @description:
 */
public class TouchView extends View {


    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("touchView", "点击了");
        final float x = event.getX();
        final float y = event.getY();
        //getActionMasked代替getAction,支持多点触控
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return super.onTouchEvent(event);
    }
}
