package com.hzy.baselib.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.hzy.baselib.R
import com.hzy.baselib.listener.RetryClickListener
import com.hzy.baselib.util.LoadingUtil
import com.hzy.baselib.util.ToastUtil
import com.hzy.baselib.widget.gloading.Gloading


fun Fragment.showToast(content: String = "NONE") {
    ToastUtil.showToast(content)
}

fun Context.showToast(content: String = "NONE") {
    ToastUtil.showToast(content)
}

fun Context.getContext(): Context {
    return this
}

fun Fragment.jump(clazz: Class<*>) {
    val intent = Intent(context, clazz)
    startActivity(intent)
}

fun Context.jump(clazz: Class<*>) {
    val intent = Intent(this, clazz)
    startActivity(intent)
}

fun Fragment.jump(clazz: Class<*>, bundle: Bundle) {
    val intent = Intent(context, clazz)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun Context.jump(clazz: Class<*>, bundle: Bundle) {
    val intent = Intent(this, clazz)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun Any?.toStringOrNull(): String {
    return this?.toString() ?: ""
}

fun Fragment.showLoading(content: String = getString(R.string.loading)) {
    LoadingUtil.show(this.activity!!, content)
}

fun Activity.showLoading(content: String = getString(R.string.loading)) {
    LoadingUtil.show(this, content)
}

fun Fragment.dismissLoading() {
    LoadingUtil.dismiss()
}

fun Activity.dismissLoading() {
    LoadingUtil.dismiss()
}

fun View.initLoadDialog(retry: RetryClickListener): Gloading.Holder {//该view不能是根布局
    return Gloading.getDefault().wrap(this).withRetry(retry)
}




