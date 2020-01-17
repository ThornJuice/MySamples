package com.hzy.baselib.util

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.text.TextUtils

import java.lang.reflect.Field


object UserInfoUtils {

    private val SP_USER_INFO = "sp_user_info"

    /**
     * 获取SharedPreferences
     *
     * @param context
     * @return
     */
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE)
    }

    /**
     * 获取Editor
     *
     * @param context
     * @return
     */
    fun getEditor(context: Context): Editor {
        return getSharedPreferences(context).edit()
    }

    /**
     * 获取用户的配置信息
     *
     * @param context
     * @param paramName
     * @return
     */
    fun getUserInfo(context: Context, paramName: String): String? {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(paramName, "")
    }

    /**
     * 保存用户的配置信息
     *
     * @param context
     * @param paramName
     * @param paramValue
     */
    fun saveUserInfo(
        context: Context, paramName: String,
        paramValue: String
    ) {
        val edit = getEditor(context)
        if (!TextUtils.isEmpty(paramValue)) {
            edit.putString(paramName, paramValue)
            edit.commit()
        }
    }


    /**
     * 保存用户信息到sp
     */
    fun saveUserInfo(context: Context, model: Any?) {
        if (model != null) {
            val preferences = context.getSharedPreferences(
                SP_USER_INFO, Context.MODE_PRIVATE
            )
            val field = model.javaClass.declaredFields
            val editor = preferences.edit()
            try {
                for (f in field) {
                    f.isAccessible = true
                    if (f.get(model) == null || TextUtils.isEmpty(f.get(model).toString())) {
                        continue
                    }
                    editor.putString(f.name, f.get(model).toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                editor.commit()
            }
        }
    }


    /**
     * 清空信息
     *
     * @param mContext
     * @return
     */
    fun clearUserInfo(mContext: Context) {
        // 载入配置文件
        val sp = mContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE)
        // 写入配置文件
        val spEd = sp.edit()
        spEd.clear()
        spEd.commit()
    }

    /**
     * 修改保存的用户信息
     *
     * @param mContext
     * @return
     */
    fun updateUserInfo(mContext: Context, paramName: String) {
        // 载入配置文件
        val sp = mContext.getSharedPreferences(SP_USER_INFO, Context.MODE_PRIVATE)
        // 写入配置文件
        val spEd = sp.edit()
        spEd.putString(paramName, "")
        spEd.commit()
    }
}
