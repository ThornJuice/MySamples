package com.hzy.wan.activity;

import com.hzy.wan.R;
import com.hzy.wan.adapter.BannerViewHolder;
import com.hzy.wan.bean.BannerBean;
import com.ju.baselibrary.base.BaseActivity;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.holder.HolderCreator;

public class Main2Activity extends BaseActivity {
    private BannerViewPager<BannerBean.DataBean, BannerViewHolder> bannerViewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    private void initBanner() {
        bannerViewPager
                .setAutoPlay(true)
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setInterval(5000)
                .setScrollDuration(1200)
                .setHolderCreator(new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return null;
                    }
                })
                .setOnPageChangeListener(new OnPageChangeListenerAdapter() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                    }
                })
                .setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position) {

                    }
                });
    }
}
