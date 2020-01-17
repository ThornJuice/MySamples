package com.hzy.baselib.util

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity


import com.hzy.baselib.R

import java.lang.ref.WeakReference


/**
 * loadingDialog
 */

object LoadingUtil {

    var loadingDialog: WeakReference<Dialog>? = null

    fun show(context: Activity, flag: Boolean, str: String) {

        if (context == null)
            return
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.view_loading_dialog, null)// 得到加载view
        val layout = v.findViewById<View>(R.id.dialog_view) as RelativeLayout// 加载布局
        val tvText = v.findViewById<View>(R.id.tv_text) as TextView
        tvText.text = str
        if (loadingDialog != null) return

        loadingDialog = if (context.parent != null)
            WeakReference(Dialog(context.parent, R.style.loading_dialog))// 创建自定义样式dialog
        else {
            WeakReference(Dialog(context, R.style.loading_dialog))// 创建自定义样式dialog
        }

        loadingDialog?.get()?.setCancelable(true)// 可以用“返回键”取消
        loadingDialog?.get()?.setCanceledOnTouchOutside(false)
        loadingDialog?.get()?.setContentView(
            layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )// 设置布局
        if (!context.isFinishing)
            loadingDialog?.get()?.show()
    }

    fun show(context: Activity, str: String) {
        show(context, true, str)
    }

    fun dismiss() {
        if (loadingDialog != null) {
            loadingDialog?.get()?.dismiss()
            loadingDialog = null
        }
    }
}
