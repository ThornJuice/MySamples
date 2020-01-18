package com.hzy.baselib.util

import android.util.Log


object LogUtils {

    var isDebug = true// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private val TAG = "XLogUtil"

    // 下面四个是默认tag的函数
    fun i(msg: String) {
        if (isDebug)
            Log.i(TAG, msg)
    }

    fun d(msg: String) {
        if (isDebug)
            Log.d(TAG, msg)
    }

    fun e(msg: String) {
        if (isDebug)
            Log.e(TAG, msg)
    }

    fun v(msg: String) {
        if (isDebug)
            Log.v(TAG, msg)
    }

    // 下面是传入自定义tag的函数
    fun i(tag: String, msg: String) {
        if (isDebug)
            Log.i(tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (isDebug)
            Log.i(tag, msg)
    }

    fun e(tag: String, msg: String) {
        if (isDebug)
            Log.i(tag, msg)
    }

    fun v(tag: String, msg: String) {
        if (isDebug)
            Log.i(tag, msg)
    }

    fun printStackTrace(t: Throwable?) {
        if (isDebug && t != null) t.printStackTrace()
    }

}
