package com.ju.designpatterns.observer;
//被观察者
public interface Observable1 {
    //添加观察者
    void addObserver(Observer1 observer);
    //移除观察者
    void deleteObserver(Observer1 observer);
    //通知观察者
    void notifyObservers(String context);

}
