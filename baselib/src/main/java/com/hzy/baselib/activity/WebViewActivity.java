package com.hzy.baselib.activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.hzy.baselib.R;
import com.hzy.baselib.base.BaseActivity;
import com.hzy.baselib.widget.webview.ProgressWebview;


public class WebViewActivity extends BaseActivity {

    private ProgressWebview webView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        webView = findViewById(R.id.progress_webview);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        if (TextUtils.isEmpty(title)) {
            title = "详情";
        }
        setPageTitle(title);

        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);//主要是这句
        settings.setJavaScriptEnabled(true);//启用js
        settings.setBlockNetworkImage(false);//解决图片不显示
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String url = intent.getStringExtra("url");
//        Log.e("url", url);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                String url=view.getUrl();
//                if(url.startsWith("tel:")){
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
                return true;
//                }
//                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
    //    public void onBack(View view) {
//        if (webView.canGoBack()) {
//            webView.goBack(); // goBack()表示返回WebView的上一页面
//        }else{
//            finish();
//        }
//    }

//    @Override
//    // 设置回退
//    // 5、覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        //按下返回键并且webview界面可以返回
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//
//            webView.goBack(); // goBack()表示返回WebView的上一页面
//            return true;
//        }
//        return super.onKeyDown(keyCode,event);
//    }

}
