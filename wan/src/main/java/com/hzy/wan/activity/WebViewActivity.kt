package com.hzy.wan.activity

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.LinearLayout
import com.ju.baselibrary.base.BaseActivity
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : BaseActivity() {
    private var mAgentWeb: AgentWeb? = null
    private var mUrl: String? = null
    override fun getLayoutId(): Int {
        return com.hzy.wan.R.layout.activity_web_view
    }

    override fun initView() {
        mUrl = intent.extras?.getString("url")
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(ll_root as LinearLayout, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(com.hzy.wan.R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(mUrl)
    }

    override fun initData() {

    }


    private val mWebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }
    }
    private val mWebChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            setPageTitle(title)
        }
    }
    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()

    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}
