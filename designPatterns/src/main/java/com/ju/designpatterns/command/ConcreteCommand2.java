package com.ju.designpatterns.command;

public class ConcreteCommand1 implements Command {
    Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }
}
