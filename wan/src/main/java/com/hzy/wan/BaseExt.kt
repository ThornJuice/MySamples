package com.hzy.wan

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ju.baselibrary.base.BaseBean
import com.ju.baselibrary.callback.RetryClickListener
import com.ju.baselibrary.utils.LoadingUtil
import com.ju.baselibrary.utils.ToastUtil
import com.ju.baselibrary.widget.gloading.Gloading

fun Fragment.showToast(content: String) {
    ToastUtil.showToast(this.activity?.applicationContext, content)
}

fun Context.showToast(content: String) {
    ToastUtil.showToast(this.applicationContext, content)
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

fun Fragment.showLoadDialog(content: String = getString(R.string.loading)) {
    LoadingUtil.show(this.activity, content)
}

fun Activity.showLoadDialog(content: String = getString(R.string.loading)) {
    LoadingUtil.show(this, content)
}

fun Fragment.dismissLoadDialog() {
    LoadingUtil.dismiss()
}

fun Activity.dismissLoadDialog() {
    LoadingUtil.dismiss()
}
fun View.initLoadDialog(retry:RetryClickListener): Gloading.Holder {//该view不能是根布局
    return Gloading.getDefault().wrap(this).withRetry(retry)
}
fun <T> BaseBean<T>.convert(): T {
    if (code == "200") {
        return data
    } else {
        throw Exception(message)
    }
}



