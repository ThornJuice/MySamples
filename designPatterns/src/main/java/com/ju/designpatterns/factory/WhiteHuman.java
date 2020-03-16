package com.ju.designpatterns.factory;

public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("white color");
    }

    @Override
    public void talk() {
        System.out.println("talk");
    }
}
