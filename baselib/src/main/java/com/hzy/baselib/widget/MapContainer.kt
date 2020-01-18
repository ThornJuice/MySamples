package com.hzy.baselib.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout

class MapContainer : RelativeLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            requestDisallowInterceptTouchEvent(false)
        } else {
            requestDisallowInterceptTouchEvent(true)//告诉父View不要拦截我的事件
        }

        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {//onTouchListener中返回false此方法才会被调用
        return true
    }
}

