package com.ju.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ju.baselibrary.R;
import com.ju.baselibrary.utils.StatusBarUtil;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_base);
        setStatusBar();
        FrameLayout base_container = findViewById(R.id.fl_container);
        base_container.addView(getLayoutInflater().inflate(getLayoutId(), null));
        init();
        initView();
        initData();
    }
    /**
     * 加载布局
     */
    @LayoutRes
    protected abstract int  getLayoutId();
    protected abstract void init();
    protected abstract void initView();
    protected abstract void initData();
    /**
     * 默认设置透明状态栏
     * */
    protected  void  setStatusBar(){
        StatusBarUtil.setImmersiveStatusBar(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
