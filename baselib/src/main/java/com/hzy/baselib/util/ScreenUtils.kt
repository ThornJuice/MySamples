package com.hzy.baselib.util

import android.app.Activity
import android.content.Context
import android.view.Window
import android.view.WindowManager

import java.lang.reflect.Field


object ScreenUtils {
    /**
     * 获取屏幕宽
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕高
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun getStatusBarHeight(context: Context): Int {
        var c: Class<*>? = null
        var obj: Any? = null
        var field: Field? = null
        var x = 0
        var statusBarHeight = 0
        try {
            c = Class.forName("com.android.internal.R\$dimen")
            obj = c!!.newInstance()
            field = c.getField("status_bar_height")
            x = Integer.parseInt(field!!.get(obj).toString())
            statusBarHeight = context.resources.getDimensionPixelSize(x)
            return statusBarHeight
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return statusBarHeight
    }

    /**
     * 改变Activity的背景的透明度，以显示变暗的效果
     *
     * @param activity 显示分享的Activity
     * @param alpha    Activity显示的透明度，1为不透明，显示正常的效果
     */
    fun setWindowDim(activity: Activity, alpha: Float) {
        val window = activity.window
        val attributes = window.attributes
        attributes.alpha = alpha
        window.attributes = attributes
    }
}
