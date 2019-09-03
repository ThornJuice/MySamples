package com.hzy.smartfield.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.hzy.smartfield.R
import com.hzy.smartfield.utils.StatusBarUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.base_title_bar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_base)
        base_container.addView(layoutInflater.inflate(setLayoutResourceID(), null))
        StatusBarUtil.setImmersiveStatusBar(this)
        EventBus.getDefault().register(this)
        setBack()
        init()
        initView()
        initData()
    }

    @LayoutRes
    protected abstract fun setLayoutResourceID(): Int

    abstract fun init()
    abstract fun initView()
    abstract fun initData()

    /**
     * 获取权限处理类
     */
    protected val rxPermissions: RxPermissions by lazy {
        RxPermissions(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    open fun setTitleText(title: String) {
        tvTitle.setText(title)
    }

    open fun setRightTitleText(title: String) {
        tvRightTitle.setText(title)
    }

    open fun setLeftImage(res: Int) {
        ivLeftImage.setImageResource(res)
    }

    open fun setBack() {
        ivLeftImage.setOnClickListener {
            this.finish()
        }
    }

    open fun setRightImage(res: Int) {
        ivRightImage.setImageResource(res)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: Any) {

    }

}
