package com.ju.designpatterns.mediator;

public class ConcreteSubject2 extends Subject {
    public ConcreteSubject2(Mediator mediator) {
        super(mediator);
    }

    public void selfMethod() {
        System.out.println("ConcreteSubject2");
    }
    public void doSomething() {
        super.mediator.doSomething();
    }
}
