package com.hzy.mvpsample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.example.mvpsample.R;
import com.ju.baselibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BlankFragment fragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        fragment =new BlankFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_container,fragment);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void initData() {

    }
}
