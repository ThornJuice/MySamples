package com.ju.jetpackcompose;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: wxj
 * @date: 2021/6/8
 * @description:
 */
public class MainActivity extends AppCompatActivity {
    WebView webView;
    RecyclerView recyclerView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView =  findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://school.jsvry.cn/article/detail/waVnOYW5Wk");
        Log.e(TAG, "onCreate: 显示");
        initView();
    }

    private void initView() {

    }

}