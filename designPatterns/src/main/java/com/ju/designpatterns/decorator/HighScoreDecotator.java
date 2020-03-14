package com.ju.designpatterns.decorator;

public class HighScoreDecotator extends  Decorator {
    public HighScoreDecotator(SchoolReport schoolReport) {
        super(schoolReport);
    }
    private  void reportHighScore(){
        System.out.println("reportHighScore");
    }

    @Override
    public void report() {
        reportHighScore();
        super.report();
    }

}
