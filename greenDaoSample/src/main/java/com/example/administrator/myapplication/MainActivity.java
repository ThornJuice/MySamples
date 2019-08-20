package com.example.administrator.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.myapplication.adapter.PagerAdapter;
import com.example.administrator.myapplication.db.DbHelperUtil;
import com.example.administrator.myapplication.fragment.BlankFragment1;
import com.example.administrator.myapplication.fragment.BlankFragment2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar
        .OnTabSelectedListener, ViewPager.OnPageChangeListener{
    BottomNavigationBar bottomNavigationBar;
    List<Fragment> fragments;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        initBottomNavi();
        initData();
       // save();
       // query();
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new BlankFragment1());
        fragments.add(new BlankFragment2());
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    private void initBottomNavi() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home_icon, "待办").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.sys_set_icon, "工作台").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void initData() {

    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        bottomNavigationBar.selectTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String  o) {

    }
    @Override
    public void onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SQLiteStudioService.instance().stop();

    }
}
