package com.hzy.wan;

import com.ju.baselibrary.base.BaseApp;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.mmkv.MMKV;

public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        String rootDir = MMKV.initialize(this);
        System.out.println("mmkv root: " + rootDir);
        MMKV kv = MMKV.defaultMMKV();


    }
}
