package com.ju.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SharedPreferences工具类
 */

public class SPUtils {
    public static SharedPreferences sp;
    private final static String SP_NAME = "MY_SharedPreferences";

    //-------------------------------公共保存数据方法---------------------------------------

    /**
     * 保存布尔值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putBoolean(key, value).commit();

    }

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value, String sp_name) {
        if (sp == null)
            sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putString(key, value).commit();

    }

    /**
     * 保存long型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 保存int型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 保存float型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveFloat(Context context, String key, float value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 获取字符值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue, String sp_name) {
        if (sp == null)
            sp = context.getSharedPreferences(sp_name, 0);
        return sp.getString(key, defValue);
    }

    /**
     * 获取int值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getInt(key, defValue);
    }

    /**
     * 获取long值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, long defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getLong(key, defValue);
    }

    /**
     * 获取float值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(Context context, String key, float defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);

        return sp.getFloat(key, defValue);
    }

    /**
     * 获取布尔值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getBoolean(key, defValue);
    }

    /**
     * @param context
     * @param key
     * @param list    保存list
     */
    public static void saveList(Context context, String key, List<String> list) {
        Set<String> stringSet = null;
        if (list != null && list.size() > 0) {
            stringSet = new HashSet<>();
            for (String value : list) {
                stringSet.add(value);
            }
        } else {
            stringSet = null;
        }
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
            sp.edit().putStringSet(key, stringSet).commit();
        } else {
            sp.edit().putStringSet(key, stringSet).commit();
        }
    }

    /**
     * @param context
     * @param key
     * @return 获取list集合
     */
    public static List<String> getList(Context context, String key) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        List<String> list = new ArrayList<>();
        try {
            for (String value : sp.getStringSet(key, null)) {
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }
}