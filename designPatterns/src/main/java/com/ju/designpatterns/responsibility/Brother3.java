package com.ju.designpatterns.responsibility;

public class Brother3 extends Handler {

    public Brother3(int level) {
        super(level);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println(women.getRequest());
        System.out.println("Brother3 agree");
    }
}