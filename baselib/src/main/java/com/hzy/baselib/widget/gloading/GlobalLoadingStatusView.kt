package com.hzy.baselib.widget.gloading

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.core.content.ContextCompat

import com.hzy.baselib.R
import com.hzy.baselib.listener.RetryClickListener
import com.hzy.baselib.util.NetWorkUtil


/**
 * simple loading status view for global usage
 *
 * @author billy.qi
 * @since 19/3/19 23:12
 */
@SuppressLint("ViewConstructor")
class GlobalLoadingStatusView
    (context: Context, private val retryClickListener: RetryClickListener?) : LinearLayout(context),
    View.OnClickListener {
    private val mTextView: TextView
    private val mImageView: ImageView

    init {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.view_global_loading_status, this, true)
        mImageView = findViewById(R.id.image)
        mTextView = findViewById(R.id.text)
        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
    }

    fun setMsgViewVisibility(visible: Boolean) {
        mTextView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setStatus(status: Int) {
        var show = true
        var onClickListener: View.OnClickListener? = null
        var image = R.drawable.loading
        var str = R.string.str_none
        when (status) {
            Gloading.STATUS_LOAD_SUCCESS -> show = false
            Gloading.STATUS_LOADING -> str = R.string.loading
            Gloading.STATUS_LOAD_FAILED -> {
                str = R.string.load_failed
                image = R.mipmap.icon_failed
                val networkConn = NetWorkUtil.isConnected(context)
                if (networkConn != null && !networkConn) {
                    str = R.string.load_failed_no_network
                    image = R.mipmap.icon_no_wifi
                }
                onClickListener = this
            }
            Gloading.STATUS_EMPTY_DATA -> {
                str = R.string.empty
                image = R.mipmap.icon_empty
            }
            else -> {
            }
        }
        mImageView.setImageResource(image)
        setOnClickListener(onClickListener)
        mTextView.setText(str)
        visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onClick(v: View) {
        retryClickListener?.retry()
    }
}
