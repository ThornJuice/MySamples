package com.hzy.baselib.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.core.content.ContextCompat;
import com.hzy.baselib.R;


public class ProgressWebview extends WebView {
    private ProgressView progressView;//进度条
    private Context context;

    public ProgressWebview(Context context) {
        this(context, null);
    }

    public ProgressWebview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        //初始化进度条
        progressView = new ProgressView(context);
        progressView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, 4)));
        progressView.setColor(ContextCompat.getColor(context, R.color.theme));
        progressView.setProgress(10);
        //把进度条加到Webview中
        addView(progressView);
        //初始化设置
//        initWebSettings();
        setWebChromeClient(new MyWebCromeClient());
//        setWebViewClient(new MyWebviewClient());
    }

    private class MyWebCromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                //加载完毕进度条消失
                progressView.setVisibility(View.GONE);
            } else {
                //更新进度
                progressView.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
