package com.hzy.baselib.util

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes

import com.hzy.baselib.R
import com.hzy.baselib.base.BaseApp


object ToastUtil {
    fun showToast(context: Context, toastStr: String) {
        try {
            val toast = Toast(context)
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            val toastLayout = LayoutInflater.from(context).inflate(R.layout.toast_view, null) as LinearLayout
            val txtToast = toastLayout.findViewById<View>(R.id.txt_toast) as TextView
            txtToast.text = toastStr + ""
            toast.view = toastLayout
            toast.show()
        } catch (e: Exception) {

        }

    }

    fun showToast(toastStr: String) {
        try {
            val toast = Toast(BaseApp.instance)
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            val toastLayout = LayoutInflater.from(BaseApp.instance).inflate(R.layout.toast_view, null) as LinearLayout
            val txtToast = toastLayout.findViewById<View>(R.id.txt_toast) as TextView
            txtToast.text = toastStr + ""
            toast.view = toastLayout
            toast.show()
        } catch (e: Exception) {

        }

    }

    fun showToast(activity: Activity, toastStr: String) {
        try {
            val toast = Toast(activity)
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            val inflater = activity.layoutInflater
            val toastLayout = inflater.inflate(R.layout.toast_view, null) as LinearLayout
            val txtToast = toastLayout.findViewById<View>(R.id.txt_toast) as TextView
            txtToast.text = toastStr + ""
            toast.view = toastLayout
            toast.show()
        } catch (e: Exception) {
        }

    }

    fun showToast(activity: Activity, @StringRes toastStr: Int) {
        try {
            val toast = Toast(activity)
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.CENTER, 0, 0)
            val inflater = activity.layoutInflater
            val toastLayout = inflater.inflate(R.layout.toast_view, null) as LinearLayout
            val txtToast = toastLayout.findViewById<View>(R.id.txt_toast) as TextView
            txtToast.setText(toastStr)
            toast.view = toastLayout
            toast.show()
        } catch (e: Exception) {

        }

    }


}
