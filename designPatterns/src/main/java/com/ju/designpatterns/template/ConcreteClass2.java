package com.ju.designpatterns.template;

public class ConcreteClass2 extends AbstractClass {
    @Override
    public void doSomething() {
        System.out.println("ConcreteClass2 doSomething");
    }

    @Override
    public void doAnything() {
        System.out.println("ConcreteClass2 doAnything");
    }
}
