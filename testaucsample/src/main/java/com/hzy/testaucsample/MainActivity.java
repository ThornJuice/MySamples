package com.hzy.testaucsample;


import android.annotation.SuppressLint;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.ju.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CommunityMallHomeAdapter adapter;
    List<CommunityMallHomeBean.DataBean.RecordsBean> list = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    boolean flag;
    int position;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private  String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new CommunityMallHomeAdapter(list);
        adapter.setType(0);
        recyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            CommunityMallHomeBean.DataBean.RecordsBean bean = new CommunityMallHomeBean.DataBean.RecordsBean();
            list.add(bean);
        }
        adapter.notifyDataSetChanged();


        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    manager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    adapter.setType(0);
                    adapter.notifyDataSetChanged();
                    flag = false;
                } else {
                    StaggeredGridLayoutManager layoutManager =
                            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                    recyclerView.setLayoutManager(layoutManager);
                    adapter.setType(1);
                    adapter.notifyDataSetChanged();
                    flag = true;
                }
            }
        });
    }




}
