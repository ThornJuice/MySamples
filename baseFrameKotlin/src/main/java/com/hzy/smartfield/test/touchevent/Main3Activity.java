package com.hzy.smartfield.test.touchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hzy.smartfield.R;
import com.hzy.smartfield.utils.ToastUtil;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//        findViewById(R.id.myLinearLayout).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("MyLinearLayout", "父View被点击");
//                return true;
//            }
//        });
        findViewById(R.id.myButton).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("MyLinearLayout", "子View被点击");
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Boolean result = super.dispatchTouchEvent(ev);
        Log.e("Main3Activity", "Activity分发:dispatchTouchEvent......" +result+ ev.getAction());
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Boolean result = super.onTouchEvent(event);
        Log.e("Main3Activity", "Activity处理:onTouchEvent......" + result+ event.getAction());
        return result;
    }
}
