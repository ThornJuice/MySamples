package com.hzy.smartfield.test.touchevent;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Boolean result = super.dispatchTouchEvent(ev);
        Log.e("MyButton", "子View分发:dispatchTouchEvent......" +result+ ev.getAction());
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Boolean result = super.onTouchEvent(event);
        Log.e("MyButton", "子Vie处理:onTouchEvent......" + result+ event.getAction());
        return result;
    }
}
