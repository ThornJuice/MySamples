package com.ju.designpatterns.command;

public class ConcreteReceiver extends Receiver {
    @Override
    void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    void doAnything() {
        System.out.println("doAnything");
    }
}
