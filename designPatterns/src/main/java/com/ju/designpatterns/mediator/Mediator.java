package com.ju.designpatterns.mediator;

public abstract class Mediator {
    protected ConcreteSubject1 subject1;
    protected ConcreteSubject2 subject2;

    public Mediator() {
        subject1 = new ConcreteSubject1(this);
        subject2 = new ConcreteSubject2(this);
    }

    public ConcreteSubject1 getSubject1() {
        return subject1;
    }

    public void setSubject1(ConcreteSubject1 subject1) {
        this.subject1 = subject1;
    }

    public ConcreteSubject2 getSubject2() {
        return subject2;
    }

    public void setSubject2(ConcreteSubject2 subject2) {
        this.subject2 = subject2;
    }

    public abstract void doSomething();
}
