package com.hzy.wan.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.hzy.wan.App
import com.hzy.wan.R
import com.hzy.wan.bean.BannerBean
import com.hzy.wan.util.GlideUtil
import com.zhpan.bannerview.holder.ViewHolder

class BannerViewHolder : ViewHolder<BannerBean.DataBean> {
    private var mImageView: ImageView? = null
    private var mTextView: TextView? = null


    override fun getLayoutId(): Int {
        return R.layout.item_home_banner
    }

    override fun onBind(itemView: View, item: BannerBean.DataBean, position: Int, size: Int) {
        mImageView = itemView.findViewById(R.id.banner_image)
        mTextView = itemView.findViewById(R.id.tv_describe)
        GlideUtil.instance.displayRadiusImage(item.imagePath, mImageView!!)
        //mTextView.setText(item.getTitle());
    }
}

