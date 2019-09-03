package com.hzy.smartfield.base

import android.content.Context
import android.support.v4.app.Fragment
import com.hzy.smartfield.utils.ToastUtil

fun Fragment.showToast(content: String) {
    ToastUtil.myToast(this?.activity?.applicationContext, content)
}

fun Context.showToast(content: String) {
    ToastUtil.myToast(this, content)
}