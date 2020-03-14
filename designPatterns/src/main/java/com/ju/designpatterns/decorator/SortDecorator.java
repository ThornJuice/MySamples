package com.ju.designpatterns.decorator;

public class SortDecorator extends  Decorator {

    public SortDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }
    public void reportSort() {
        System.out.println("reportSort");
    }

    @Override
    public void report() {
        super.report();
        reportSort();
    }
}
