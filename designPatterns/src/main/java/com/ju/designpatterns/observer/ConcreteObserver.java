package com.ju.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class ConcreteObserver implements Observer {

    String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

//    @Override
//    public void update(String context) {
//        System.out.println("被观察者行动," + name + "开始汇报");
//        doReport(context);
//        System.out.println(name + "汇报完毕");
//    }
    @Override
    public void update(Observable observable, Object o) {
        System.out.println("被观察者行动," + name + "开始汇报");
        doReport(o.toString());
        System.out.println(name + "汇报完毕");
    }
    private void doReport(String context) {
        System.out.println(context);
    }
}
