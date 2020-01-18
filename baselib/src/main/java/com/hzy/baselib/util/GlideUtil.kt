package com.hzy.baselib.util

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop
import com.bumptech.glide.request.RequestOptions
import com.hzy.baselib.base.BaseApp


object GlideUtil {
    /**
     * 加载网络图片
     * */
    @SuppressLint("CheckResult")
    fun displayImage(url: String?, imageView: ImageView) {
        val options = RequestOptions()
        options.fitCenter()
        options.placeholder(com.hzy.baselib.R.mipmap.loading)
        Glide.with(BaseApp.instance).load(url).apply(options).into(imageView)
    }

    /**
     * 加载本地图片资源
     * */
    fun displayImage(resourceId: Int?, imageView: ImageView) {
        val options = RequestOptions()
        Glide.with(BaseApp.instance).load(resourceId).apply(options).into(imageView)
    }

    /**
     * 加载圆角图片
     * */
    fun displayRadiusImage(url: String, imageView: ImageView, radius: Float = 5f) {
        val roundedCorners = RoundedCorners(ScreenUtils.dip2px(BaseApp.instance, radius))
        val options = RequestOptions.bitmapTransform(roundedCorners).override(0, 0)
        Glide.with(BaseApp.instance).load(url).apply(options).into(imageView)
    }

    /**
     * 加载圆形图片
     * */
    fun displayRoundImage(url: String, imageView: ImageView) {
        val roundedCorners = RoundedCorners(30)
        val options = RequestOptions.bitmapTransform(roundedCorners)
            .circleCrop()
        Glide.with(BaseApp.instance).load(url).apply(options).into(imageView)

    }
}