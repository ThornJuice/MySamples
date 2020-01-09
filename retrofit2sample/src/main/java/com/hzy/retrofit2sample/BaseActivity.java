package com.hzy.retrofit2sample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hzy.retrofit2sample.util.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract  class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        StatusBarUtil.setImmersiveStatusBar(this);
        FrameLayout contentView = findViewById(R.id.contentView);
        View view = View.inflate(this, getLayoutId(), null);
        contentView.removeAllViews();
        contentView.addView(view, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mUnbinder = ButterKnife.bind(this);
        init();
    }
    public abstract void init();
    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }
}
