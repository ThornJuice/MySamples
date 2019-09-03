package com.hzy.smartfield.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.hzy.smartfield.R;

import java.lang.reflect.Field;

public class StatusBarUtil {
    /**
     * 设置沉浸式状态栏
     *
     * @param activity
     */
    public static void setImmersiveStatusBar(Activity activity) {
        //5.0以上版本直接设置状态栏颜色透明度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏颜色
                window.setStatusBarColor(ContextCompat.getColor(activity, R.color.blue_theme));
                //设置导航栏透明
                window.setNavigationBarColor(ContextCompat.getColor(activity, android.R.color.transparent));
                //修改状态栏文字颜色为黑色
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //设置FitsSystemWindows 为true
                ViewGroup contentView = activity.findViewById(android.R.id.content);
                View childAt = contentView.getChildAt(0);
                if (childAt != null) {
                    childAt.setFitsSystemWindows(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //4.4-5.0版本通过伪装一个状态栏来设置颜色和透明度
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
                View childAt = contentView.getChildAt(0);
                if (childAt != null) {
                    childAt.setFitsSystemWindows(true);
                }
                View view = new View(activity);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity)));
                view.setBackgroundColor(ContextCompat.getColor(activity,  R.color.blue_theme));
                contentView.addView(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单独修改导航栏颜色
     *
     * @param colorId
     * @param activity
     */
    public static void setNavigationBarColor(Activity activity, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Window window = activity.getWindow();
                window.setNavigationBarColor(ContextCompat.getColor(activity, colorId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单独修改状态栏颜色
     *
     * @param colorId
     * @param activity
     */
    public static void setStatusBarColor(Activity activity, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(activity, colorId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单独修改状态栏文字颜色
     *
     * @param isdark   true 黑色  false白色
     * @param activity
     */
    public static void setStatusBarTextColor(Activity activity, boolean isdark) {
        try {
            View decor = activity.getWindow().getDecorView();
            if (isdark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
