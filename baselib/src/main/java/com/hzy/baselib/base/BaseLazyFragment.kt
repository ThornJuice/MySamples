package com.hzy.baselib.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment

import com.hzy.baselib.widget.gloading.Gloading


abstract class BaseLazyFragment : Fragment() {
    protected var mLoadHolder: Gloading.Holder? = null
    protected var mContext: Context? = null
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepared = false
    /**
     * 是否已经执行过懒加载
     */
    private var isLazyLoaded = false
    /**
     * 加载布局
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun init()

    protected abstract fun initView(view: View)
    /**
     * 懒加载
     */
    @UiThread
    protected abstract fun lazyLoad()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepared = true
        init()
        initView(view)
        lazyLoadDataIfPrepared()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepared && !isLazyLoaded) {
            lazyLoad()
            isLazyLoaded = true
        }
    }
}
