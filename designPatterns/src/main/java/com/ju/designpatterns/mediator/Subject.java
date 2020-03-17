package com.ju.designpatterns.mediator;

public abstract class Subject {
    protected Mediator mediator;

    public Subject(Mediator mediator) {
        this.mediator = mediator;
    }
}
