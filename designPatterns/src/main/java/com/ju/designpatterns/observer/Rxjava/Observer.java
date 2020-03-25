package com.ju.designpatterns.observer.Rxjava;
//订阅者(观察者)
public interface Observer<T> {
        void onCompleted();
        void onError(Throwable throwable);
        void onNext(T value);
}
