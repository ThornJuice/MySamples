package com.hzy.testaucsample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CommunityMallHomeAdapter extends BaseQuickAdapter<CommunityMallHomeBean.DataBean.RecordsBean, BaseViewHolder> {
    private int type = 0;

    public CommunityMallHomeAdapter(@Nullable List<CommunityMallHomeBean.DataBean.RecordsBean> data) {
        super(R.layout.item_community_mall_home, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommunityMallHomeBean.DataBean.RecordsBean item) {

        ImageView imageView = helper.getView(R.id.iv_image);
        LinearLayout ll1 = helper.getView(R.id.ll1);
        LinearLayout ll2 = helper.getView(R.id.ll2);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (type == 1) {
            ll1.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
            if (helper.getLayoutPosition() % 2 == 0) {
                layoutParams.height = ScreenUtils.dip2px(mContext, 170);

            } else {
                layoutParams.height = ScreenUtils.dip2px(mContext, 190);
            }
            layoutParams.width = ScreenUtils.dip2px(mContext, (ScreenUtils.getScreenWidth(mContext) - 100) / 2);
            imageView.setLayoutParams(layoutParams);
        }else{
            ll2.setVisibility(View.VISIBLE);
            ll1.setVisibility(View.GONE);
        }
    }

    public void setType(int type) {
        this.type = type;
    }
}