package com.ju.designpatterns.decorator;

public class SugarFourthGradeSchoolReport extends  FourthGradeSchoolReport {
    private  void reportHighScore(){
        System.out.println("reportHighScore");
    }
    private  void reportSort(){
        System.out.println("reportSort");
    }

    @Override
    public void report() {
        reportHighScore();
        super.report();
        reportSort();
    }
}
