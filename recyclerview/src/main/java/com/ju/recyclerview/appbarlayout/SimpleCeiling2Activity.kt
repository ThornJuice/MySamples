package com.ju.recyclerview.appbarlayout

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.ju.recyclerview.R
import kotlinx.android.synthetic.main.activity_simple_ceiling2.*

/**
 * 常用吸顶效果
 * */
class SimpleCeiling2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_ceiling2)
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://juejin.im/post/5e94815551882573af79b2a0")
    }
}