package com.hzy.baselib.util

import android.annotation.SuppressLint
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hzy.baselib.R
import com.hzy.baselib.base.BaseApp//设置图片圆角角度

object GlideUtil {
    @SuppressLint("CheckResult")
    fun displayImage(url: String?, imageView: ImageView) {
        val options = RequestOptions()
        options.fitCenter()
        options.placeholder(R.mipmap.loading)
        Glide.with(BaseApp.instance).load(url).apply(options).into(imageView)
    }

    fun displayRadiusImage(url: String, imageView: ImageView, radius: Float = 5f) {
        //设置图片圆角角度
        val roundedCorners = RoundedCorners(ScreenUtils.dip2px(BaseApp.instance, radius))
        val options = RequestOptions.bitmapTransform(roundedCorners).override(0, 0)
        Glide.with(BaseApp.instance).load(url).apply(options).into(imageView)
    }
}