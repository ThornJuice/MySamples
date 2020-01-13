package com.hzy.retrofit2sample;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hzy.retrofit2sample.util.StatusBarUtil;



public abstract  class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        StatusBarUtil.setImmersiveStatusBar(this);
        FrameLayout contentView = findViewById(R.id.contentView);
        View view = View.inflate(this, getLayoutId(), null);
        contentView.removeAllViews();
        contentView.addView(view, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        init();
    }
    public abstract void init();
    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
