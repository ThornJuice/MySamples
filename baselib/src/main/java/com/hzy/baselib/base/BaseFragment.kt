package com.hzy.baselib.base


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

import com.hzy.baselib.widget.gloading.Gloading


abstract class BaseFragment : Fragment() {
    protected var mLoadHolder: Gloading.Holder? = null
    protected var mContext: Context? = null

    /**
     * 加载布局
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    protected abstract fun init()

    protected abstract fun initView(view: View)

    protected abstract fun initData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView(view)
        initData()
    }
}
