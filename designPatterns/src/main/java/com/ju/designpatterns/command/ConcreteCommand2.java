package com.ju.designpatterns.command;

public class ConcreteCommand2 implements Command {
    Receiver receiver;

    public ConcreteCommand2(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doAnything();
    }
}
