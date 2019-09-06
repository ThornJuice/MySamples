package com.ju.baselibrary.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseLazyFragment extends Fragment {
    /**
     * 视图是否加载完毕
     */
    private boolean isViewPrepared = false;
    /**
     * 是否已经执行过懒加载
     */
    private boolean isLazyLoaded = false;

    protected abstract void init();

    protected abstract void initView(View view);
    /**
     * 懒加载
     */
    @UiThread
    protected abstract void lazyLoad();
    /**
     * 加载布局
     */
    @LayoutRes
    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        init();
        initView(view);
        lazyLoadDataIfPrepared();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            lazyLoadDataIfPrepared();
        }
    }
    private  void lazyLoadDataIfPrepared(){
        if(getUserVisibleHint()&&isViewPrepared&&!isLazyLoaded){
            lazyLoad();
            isLazyLoaded = true;
        }
    }
}