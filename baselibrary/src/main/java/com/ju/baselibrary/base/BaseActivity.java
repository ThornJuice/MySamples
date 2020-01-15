package com.ju.baselibrary.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.ju.baselibrary.R;
import com.ju.baselibrary.utils.StatusBarUtil;
import com.ju.baselibrary.widget.BaseTitleBar;
import com.ju.baselibrary.widget.gloading.Gloading;

public abstract class BaseActivity extends AppCompatActivity {
    protected Gloading.Holder mHolder;
    protected BaseTitleBar baseTitleBar;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_base);
        FrameLayout base_container = findViewById(R.id.fl_container);
        base_container.addView(getLayoutInflater().inflate(getLayoutId(), null));
        initTitleBar();
        initView();
        initData();
    }

    /**
     * 加载布局
     */
    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 默认设置透明状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setImmersiveStatusBar(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initTitleBar() {
        baseTitleBar = findViewById(R.id.title_bar);
        baseTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setPageTitle(String title) {
        baseTitleBar.setPageTitle(TextUtils.isEmpty(title) ? "标题" : title);
    }
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(this).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    protected void onLoadRetry() {
        // override this method in subclass to do retry task
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }
}
