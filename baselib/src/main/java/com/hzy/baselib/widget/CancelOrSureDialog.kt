package com.hzy.baselib.widget

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.hzy.baselib.R
import com.hzy.baselib.util.ScreenUtils


open class CancelOrSureDialog(context: Activity, title: String) : Dialog(context, R.style.custom_dialog) {

    init {
        //指定布局
        this.setContentView(R.layout.dialog_cancel_or_ok)
        //点击外部不可消失
        this.setCancelable(false)
        val llBack = this.findViewById<View>(R.id.ll_dialog_back) as LinearLayout
        setWindowSize(llBack, context)
        //设置标题
        val titleTv = this.findViewById<View>(R.id.dialog_title_tv) as TextView
        titleTv.text = title

        this.findViewById<View>(R.id.cancel_tv).setOnClickListener {
            //取消
            cancel()
        }

        this.findViewById<View>(R.id.ok_tv).setOnClickListener { ok() }
        this.show()
    }

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
