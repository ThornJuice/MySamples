package com.hzy.smartfield.test.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.LogPrinter;
import android.view.MotionEvent;

import androidx.appcompat.widget.LinearLayoutCompat;

public class MyLinearLayout extends LinearLayoutCompat {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Boolean result = super.onInterceptTouchEvent(ev);
        Log.e("MyLinearLayout","父View拦截:onInterceptTouchEvent"+result+ev.getAction());
        return  result;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Boolean result = super.dispatchTouchEvent(ev);
        Log.e("MyLinearLayout", "父View分发:dispatchTouchEvent" + result+ev.getAction());
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Boolean result = super.onTouchEvent(event);
        Log.e("MyLinearLayout", "父View处理:onTouchEvent" + result+event.getAction());
        return result;
    }
}
