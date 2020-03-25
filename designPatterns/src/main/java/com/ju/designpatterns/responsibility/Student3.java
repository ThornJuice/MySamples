package com.ju.designpatterns.responsibility;

public class Student3 extends Handler {

    public Student3(int level) {
        super(level);
    }

    @Override
    protected void response(ITeacher teacher) {
        System.out.println(teacher.getTask());
        System.out.println("Student3 receive");
    }
}