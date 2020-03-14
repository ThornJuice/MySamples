package com.ju.designpatterns.decorator;

public class FourthGradeSchoolReport extends SchoolReport {
    @Override
    public void report() {
        System.out.println("report");
    }

    @Override
    public void sign() {
        System.out.println("sign");
    }
}
