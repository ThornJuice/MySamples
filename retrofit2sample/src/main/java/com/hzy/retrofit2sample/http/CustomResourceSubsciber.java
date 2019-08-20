package com.hzy.retrofit2sample.http;

import com.hzy.retrofit2sample.util.XLog;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CustomResourceSubsciber<T> extends ResourceSubscriber<T> {


    @Override
    public void onError(Throwable t) {
        XLog.e("onError");
    }

    @Override
    public void onComplete() {
        XLog.e("onComplete");
    }
}
