package com.hzy.wan.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hzy.wan.App
import com.hzy.wan.R
import com.ju.baselibrary.utils.ScreenUtils

class GlideUtil private constructor() {
    // =java静态内部类式单例
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = GlideUtil()
    }

    @SuppressLint("CheckResult")
    fun displayImage(url: String?, imageView: ImageView) {
        val options = RequestOptions()
        options.fitCenter()
        options.placeholder(R.mipmap.loading)
        Glide.with(App.getAppContext()).load(url).apply(options).into(imageView)
    }

    fun displayRadiusImage(url: String, imageView: ImageView, radius: Float = 5f) {
        //设置图片圆角角度
        val roundedCorners = RoundedCorners(ScreenUtils.dip2px(App.getAppContext(), radius))
        val options = RequestOptions.bitmapTransform(roundedCorners).override(0, 0)
        Glide.with(App.getAppContext()).load(url).apply(options).into(imageView)
        A.z
    }

}