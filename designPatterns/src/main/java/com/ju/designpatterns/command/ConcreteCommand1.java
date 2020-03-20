package com.ju.designpatterns.command;

public class ConcreteCommand implements Command {
    Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doSomething();
    }
}
