package com.ju.designpatterns.observer.rxjava;

public abstract class Subscribe<T> implements Observer<T> {
    public void onStart() {
        System.out.println("开始");
    }
}
