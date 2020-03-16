package com.ju.designpatterns.template;

public class ConcreteClass1 extends AbstractClass {
    @Override
    public void doSomething() {
        System.out.println("ConcreteClass1 doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteClass1 doAnything");
    }
}
