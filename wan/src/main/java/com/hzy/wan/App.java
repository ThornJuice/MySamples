package com.hzy.wan;

import com.ju.baselibrary.base.BaseApp;
import com.squareup.leakcanary.LeakCanary;

public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
