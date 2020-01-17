package com.hzy.baselib.widget

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.PopupWindow

class BasePopupwindow(context: Context, resId: Int, width: Int, height: Int) : PopupWindow() {
    private val mView: View

    init {
        mView = View.inflate(context, resId, null)
        this.contentView = mView
        this.width = width
        this.height = height
        this.isFocusable = true
        this.isOutsideTouchable = true
        //this.setAnimationStyle(R.style.PopupWindowAnimation);
        this.setBackgroundDrawable(ColorDrawable())
    }

    fun setViewListener(onViewCallBack: onViewCallBack) {
        onViewCallBack.setView(mView)
    }

    interface onViewCallBack {
        fun setView(view: View)
    }
}
