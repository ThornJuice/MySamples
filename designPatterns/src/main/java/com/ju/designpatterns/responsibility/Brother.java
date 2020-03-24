package com.ju.designpatterns.responsibility;

public class Brother extends Handler {

    public Brother(int level) {
        super(level);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println(women.getRequest());
        System.out.println("Brother agree");
    }
}
