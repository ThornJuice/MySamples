package com.hzy.testaucsample;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ju.baselibrary.base.BaseActivity;
import com.ju.baselibrary.utils.ToastUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

    @Override
    protected void init() {
        String content="appid=111&mch_id=111&key=111";
        String key="111";
        byte[] bytes = EncryptUtils.encryptHmacSHA256(content.getBytes(),key.getBytes());
        ToastUtils.showShort(byteArrayToHexString(bytes));
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
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new CommunityMallHomeAdapter(list);
        adapter.setType(0);
        recyclerView.setAdapter(adapter);
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
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
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
