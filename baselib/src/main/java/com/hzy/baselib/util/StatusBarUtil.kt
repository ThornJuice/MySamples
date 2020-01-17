package com.hzy.baselib.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

import java.lang.reflect.Field

object StatusBarUtil {
    /**
     * 设置沉浸式状态栏
     *
     * @param activity
     */
    fun setImmersiveStatusBar(activity: Activity) {
        //5.0以上版本直接设置状态栏颜色透明度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val window = activity.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                //设置状态栏颜色
                window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
                //设置导航栏透明
                window.navigationBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
                //修改状态栏文字颜色为黑色
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                //设置FitsSystemWindows 为true
                val contentView = activity.findViewById<ViewGroup>(android.R.id.content)
                val childAt = contentView.getChildAt(0)
                if (childAt != null) {
                    childAt.fitsSystemWindows = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            //4.4-5.0版本通过伪装一个状态栏来设置颜色和透明度
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                val window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                val contentView = activity.findViewById<ViewGroup>(android.R.id.content)
                val childAt = contentView.getChildAt(0)
                if (childAt != null) {
                    childAt.fitsSystemWindows = true
                }
                val view = View(activity)
                view.layoutParams =
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
                view.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.transparent))
                contentView.addView(view)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 单独修改导航栏颜色
     *
     * @param colorId
     * @param activity
     */
    fun setNavigationBarColor(activity: Activity, @ColorRes colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val window = activity.window
                window.navigationBarColor = ContextCompat.getColor(activity, colorId)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 单独修改状态栏颜色
     *
     * @param colorId
     * @param activity
     */
    fun setStatusBarColor(activity: Activity, @ColorRes colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                val window = activity.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(activity, colorId)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 单独修改状态栏文字颜色
     *
     * @param isdark   true 黑色  false白色
     * @param activity
     */
    fun setStatusBarTextColor(activity: Activity, isdark: Boolean) {
        try {
            val decor = activity.window.decorView
            if (isdark) {
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 获取状态栏高度
     *
     * @param context
     */
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
}
