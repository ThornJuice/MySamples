package com.ju.designpatterns.responsibility;

public class Teacher implements ITeacher {
    private int type;
    private String task;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getTask() {
        return task;
    }

    public Teacher(int type, String task) {
        this.type = type;
        this.task = task;
    }
}
