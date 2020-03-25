package com.ju.designpatterns.responsibility;

public class Student2 extends Handler {

    public Student2(int level) {
        super(level);
    }

    @Override
    protected void response(ITeacher teacher) {
        System.out.println(teacher.getTask());
        System.out.println("Student2 receive");
    }
}