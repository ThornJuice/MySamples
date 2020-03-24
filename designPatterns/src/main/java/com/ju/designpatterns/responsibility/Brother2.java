package com.ju.designpatterns.responsibility;

public class Brother2 extends Handler {

    public Brother2(int level) {
        super(level);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println(women.getRequest());
        System.out.println("Brother2 agree");
    }
}