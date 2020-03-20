package com.ju.designpatterns.state;

public class ConcreteState1 extends State {

    @Override
    void handle1() {
        //to do
        System.out.println("ConcreteState1 handle1");
    }

    @Override
    void handle2() {
        super.context.setCurrentState(Context.STATE2);
        super.context.handle2();
    }
}
