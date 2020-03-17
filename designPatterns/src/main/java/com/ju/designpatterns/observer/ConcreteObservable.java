package com.ju.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ConcreteObservable extends Observable {
//    private List<Observer1> observers = new ArrayList<>();
//    @Override
//    public void addObserver(Observer1 observer) {
//        observers.add(observer);
//    }
//
//    @Override
//    public void deleteObserver(Observer1 observer) {
//        observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers(String context) {
//        for (int i = 0; i < observers.size(); i++) {
//            observers.get(i).update(context);
//        }
//    }

    public void eat() {
        setChanged();
        notifyObservers("eating");
    }

    public void play() {
        setChanged();
        notifyObservers("playing");
    }
}
