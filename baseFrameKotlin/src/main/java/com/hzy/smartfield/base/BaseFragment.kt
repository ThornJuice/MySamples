package com.hzy.smartfield.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbruyelle.rxpermissions2.RxPermissions
import org.greenrobot.eventbus.EventBus

/**
 * @desc BaseFragment
 */
abstract class BaseFragment : Fragment() {

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun attachLayoutRes(): Int
    /**
     * 初始化 View
     */
    abstract fun initView(view: View)
    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false

    /**
     * 获取权限处理类
     */
    protected val rxPermissions: RxPermissions by lazy {
        RxPermissions(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(attachLayoutRes(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        initView(view)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }
}