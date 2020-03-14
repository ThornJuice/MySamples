package com.ju.designpatterns.decorator;

public abstract class Decorator extends SchoolReport {
    private  SchoolReport schoolReport;
    public  Decorator(SchoolReport schoolReport){
        this.schoolReport = schoolReport;
    }
    public  void report(){
        schoolReport.report();
    }
    public  void  sign(){
        schoolReport.sign();
    }
}
