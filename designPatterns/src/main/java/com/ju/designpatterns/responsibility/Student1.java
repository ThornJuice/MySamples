package com.ju.designpatterns.responsibility;

public class Student1 extends Handler {

    public Student1(int level) {
        super(level);
    }

    @Override
    protected void response(ITeacher teacher) {
        System.out.println(teacher.getTask());
        System.out.println("Student1 receive");
    }
}
