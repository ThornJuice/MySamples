package com.ju.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.lang.reflect.Field;


public class UserInfoUtils {

    private static final String SP_USER_INFO = "sp_user_info";

    /**
     * 获取SharedPreferences
     *
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE);
    }

    /**
     * 获取Editor
     *
     * @param context
     * @return
     */
    public static Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    /**
     * 获取用户的配置信息
     *
     * @param context
     * @param paramName
     * @return
     */
    public static String getUserInfo(Context context, String paramName) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString(paramName, "");
    }

    /**
     * 保存用户的配置信息
     *
     * @param context
     * @param paramName
     * @param paramValue
     */
    public static void saveUserInfo(Context context, String paramName,
                                    String paramValue) {
        Editor edit = getEditor(context);
        if (!TextUtils.isEmpty(paramValue)) {
            edit.putString(paramName, paramValue);
            edit.commit();
        }
    }


    /**
     * 保存用户信息到sp
     */
    public static void saveUserInfo(Context context, Object model) {
        if (model != null) {
            SharedPreferences preferences = context.getSharedPreferences(
                    SP_USER_INFO, Context.MODE_PRIVATE);
            Field[] field = model.getClass().getDeclaredFields();
            Editor editor = preferences.edit();
            try {
                for (Field f : field) {
                    f.setAccessible(true);
                    if (f.get(model) == null
                            || TextUtils.isEmpty(f.get(model).toString())) {
                        continue;
                    }
                    editor.putString(f.getName(), f.get(model).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                editor.commit();
            }
        }
    }


    /**
     * 清空信息
     *
     * @param mContext
     * @return
     */
    public static void clearUserInfo(Context mContext) {
        // 载入配置文件
        SharedPreferences sp = mContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE);
        // 写入配置文件
        Editor spEd = sp.edit();
        spEd.clear();
        spEd.commit();
    }

    /**
     * 修改保存的用户信息
     *
     * @param mContext
     * @return
     */
    public static void updateUserInfo(Context mContext, String paramName) {
        // 载入配置文件
        SharedPreferences sp = mContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE);
        // 写入配置文件
        Editor spEd = sp.edit();
        spEd.putString(paramName, "");
        spEd.commit();
    }
}
