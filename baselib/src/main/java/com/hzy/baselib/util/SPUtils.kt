package com.hzy.baselib.util

import android.content.Context
import android.content.SharedPreferences

import java.util.ArrayList
import java.util.HashSet

/**
 * SharedPreferences工具类
 */

object SPUtils {
    var sp: SharedPreferences? = null
    private val SP_NAME = "hzy_SharedPreferences"

    //-------------------------------公共保存数据方法---------------------------------------

    /**
     * 保存布尔值
     *
     * @param context
     * @param key
     * @param value
     */
    fun saveBoolean(context: Context, key: String, value: Boolean) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        sp!!.edit().putBoolean(key, value).commit()

    }

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     */
    fun saveString(context: Context, key: String, value: String, sp_name: String) {
        if (sp == null)
            sp = context.getSharedPreferences(sp_name, 0)
        sp!!.edit().putString(key, value).commit()

    }

    /**
     * 保存long型
     *
     * @param context
     * @param key
     * @param value
     */
    fun saveLong(context: Context, key: String, value: Long) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        sp!!.edit().putLong(key, value).commit()
    }

    /**
     * 保存int型
     *
     * @param context
     * @param key
     * @param value
     */
    fun saveInt(context: Context, key: String, value: Int) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        sp!!.edit().putInt(key, value).commit()
    }

    /**
     * 保存float型
     *
     * @param context
     * @param key
     * @param value
     */
    fun saveFloat(context: Context, key: String, value: Float) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        sp!!.edit().putFloat(key, value).commit()
    }

    /**
     * 获取字符值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    fun getString(context: Context, key: String, defValue: String, sp_name: String): String? {
        if (sp == null)
            sp = context.getSharedPreferences(sp_name, 0)
        return sp!!.getString(key, defValue)
    }

    /**
     * 获取int值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    fun getInt(context: Context, key: String, defValue: Int): Int {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        return sp!!.getInt(key, defValue)
    }

    /**
     * 获取long值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    fun getLong(context: Context, key: String, defValue: Long): Long {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        return sp!!.getLong(key, defValue)
    }

    /**
     * 获取float值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    fun getFloat(context: Context, key: String, defValue: Float): Float {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)

        return sp!!.getFloat(key, defValue)
    }

    /**
     * 获取布尔值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    fun getBoolean(
        context: Context, key: String,
        defValue: Boolean
    ): Boolean {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        return sp!!.getBoolean(key, defValue)
    }

    /**
     * @param context
     * @param key
     * @param list    保存list
     */
    fun saveList(context: Context, key: String, list: List<String>?) {
        var stringSet: MutableSet<String>? = null
        if (list != null && list.size > 0) {
            stringSet = HashSet()
            for (value in list) {
                stringSet.add(value)
            }
        } else {
            stringSet = null
        }
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0)
            sp!!.edit().putStringSet(key, stringSet).commit()
        } else {
            sp!!.edit().putStringSet(key, stringSet).commit()
        }
    }

    /**
     * @param context
     * @param key
     * @return 获取list集合
     */
    fun getList(context: Context, key: String): List<String>? {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0)
        val list = ArrayList<String>()
        try {
            for (value in sp!!.getStringSet(key, null)!!) {
                list.add(value)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return if (list != null && list.size > 0) {
            list
        } else null
    }
}