package com.hzy.baselib.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

import com.hzy.baselib.R
import com.hzy.baselib.http.HttpStringCallBack
import com.hzy.baselib.http.OkGoRequest
import com.hzy.baselib.util.ActivityUtils
import com.hzy.baselib.util.StatusBarUtil
import com.hzy.baselib.widget.BaseTitleBar
import com.hzy.baselib.widget.gloading.Gloading
import com.lzy.okgo.OkGo
import kotlinx.android.synthetic.main.activity_base.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


abstract class BaseActivity : AppCompatActivity() {
    protected var loadingUtil: Gloading.Holder? = null
    protected lateinit var context: Context
    protected lateinit var act: Activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        act = this
        setContentView(R.layout.activity_base)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        ActivityUtils.addActivity(this)
        val base_container = findViewById<FrameLayout>(R.id.fl_container)
        base_container.addView(layoutInflater.inflate(getLayoutId(), null))
        initTitleBar()
        initView()
        initData()
    }

    /**
     * 加载布局
     */
    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    protected fun setStatusBar() {
        StatusBarUtil.setImmersiveStatusBar(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        ActivityUtils.removeActivity(this)
        OkGo.getInstance().cancelTag(this)
    }

    private fun initTitleBar() {
        baseTitleBar.setLeftLayoutClickListener(View.OnClickListener {
            finish()
        })
    }
    protected fun removeTitleBar() {
        baseTitleBar.visibility = View.GONE
    }
    protected fun setPageTitle(title: String = getString(R.string.title)) {
        baseTitleBar?.setPageTitle(title)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: Any) {

    }
}
