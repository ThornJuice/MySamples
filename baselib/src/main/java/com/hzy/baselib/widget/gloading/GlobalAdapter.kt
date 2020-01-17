package com.hzy.baselib.widget.gloading

import android.view.View

import com.hzy.baselib.util.Constants


class GlobalAdapter : Gloading.Adapter {

    override fun getView(holder: Gloading.Holder, convertView: View?, status: Int): View {
        var loadingStatusView: GlobalLoadingStatusView? = null
        //reuse the old view, if possible
        if (convertView != null && convertView is GlobalLoadingStatusView) {
            loadingStatusView = convertView
        }
        if (loadingStatusView == null) {
            loadingStatusView = GlobalLoadingStatusView(holder.context, holder.retryListener)
        }
        loadingStatusView.setStatus(status)
        val data = holder.getData<Any>()
        //show or not show msg view
        val hideMsgView = Constants.HIDE_LOADING_STATUS_MSG == data
        loadingStatusView.setMsgViewVisibility(!hideMsgView)
        return loadingStatusView
    }
}
