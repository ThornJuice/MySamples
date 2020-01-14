package com.ju.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseLazyFragment extends Fragment {
    protected Context mContext;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

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
