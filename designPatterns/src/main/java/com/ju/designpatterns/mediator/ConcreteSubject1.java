package com.ju.designpatterns.mediator;

public class ConcreteSubject1 extends Subject {
    public ConcreteSubject1(Mediator mediator) {
        super(mediator);
    }

    public void selfMethod() {
        System.out.println("ConcreteSubject1");
    }

    public void doSomething() {
        super.mediator.doSomething();
    }
}
