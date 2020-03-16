package com.ju.designpatterns.factory;

public class YellowHuman implements Human {

     @Override
    public void getColor() {
        System.out.println("Yellow color");
    }

    @Override
    public void talk() {
        System.out.println("talk");
    }
}
