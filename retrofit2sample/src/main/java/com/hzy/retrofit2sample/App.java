package com.hzy.retrofit2sample;

import android.app.Application;

/**
 * Application
 */
public class App extends Application {

    private static App instance;
    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
