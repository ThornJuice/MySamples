package com.hzy.baselib.widget

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.hzy.baselib.R
import com.hzy.baselib.util.ScreenUtils


open class CancelOrOkDialog(context: Activity, title: String) : Dialog(context, R.style.custom_dialog) {

    init {
        //指定布局
        setContentView(R.layout.dialog_cancel_or_ok)
        //点击外部不可消失
        setCancelable(false)
        val llBack = findViewById<View>(R.id.ll_dialog_back) as LinearLayout
        setWindowSize(llBack, context)
        //设置标题
        val titleTv = findViewById<View>(R.id.dialog_title_tv) as TextView
        titleTv.text = title

        findViewById<View>(R.id.cancel_tv).setOnClickListener {
            //取消
            cancel()
        }

        findViewById<View>(R.id.ok_tv).setOnClickListener { ok() }
        show()
    }//使用自定义Dialog样式

    //确认
    open fun ok() {}

    private fun setWindowSize(layout: LinearLayout, context: Activity) {
        val params = layout.layoutParams as LinearLayout.LayoutParams
        val width = ScreenUtils.getScreenWidth(context)
        val height = ScreenUtils.getScreenHeight(context)
        params.width = width * 3 / 5
        params.height = height * 1 / 7
        layout.layoutParams = params
    }
}
