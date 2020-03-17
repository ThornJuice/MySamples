package com.ju.designpatterns.mediator;

public class ConcreteMediator extends Mediator {
    @Override
    public void doSomething() {
        super.subject1.selfMethod();
        super.subject2.selfMethod();
    }

}
