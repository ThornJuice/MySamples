package com.ju.designpatterns.command;

public class Invoker {
    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        command.execute();
    }
}
