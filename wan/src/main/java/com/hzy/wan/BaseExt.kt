package com.hzy.wan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ju.baselibrary.base.BaseBean
import com.ju.baselibrary.utils.ToastUtil

fun Fragment.showToast(content: String) {
    ToastUtil.showToast(this.activity?.applicationContext, content)
}

fun Context.showToast(content: String) {
    ToastUtil.showToast(this, content)
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

fun <T> BaseBean<T>.convert(): T {
    if (code == "200") {
        return data
    } else {
        throw Exception(message)
    }
}



