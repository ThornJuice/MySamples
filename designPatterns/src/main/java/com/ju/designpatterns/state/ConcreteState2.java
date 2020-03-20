package com.ju.designpatterns.state;

public class ConcreteState2 extends State {

    @Override
    void handle1() {
        super.context.setCurrentState(Context.STATE1);
        super.context.handle1();
    }

    @Override
    void handle2() {
        // to  do
        System.out.println("ConcreteState2 handle2");
    }
}
