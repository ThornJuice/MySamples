package com.ju.designpatterns.responsibility;

public abstract class Handler {
    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;
    public static final int LEVEL3 = 3;
    private int level = 0;
    private Handler nextHanlder;

    public Handler(int level) {
        this.level = level;
    }

    public final void HandlerMessage(IWomen women) {
        if (women.getType() == this.level) {
            response(women);
        } else {
            if (nextHanlder != null) {
                nextHanlder.HandlerMessage(women);
            } else {
                System.out.println("disAgree");
            }
        }
    }

    protected abstract void response(IWomen women);

    public void setNext(Handler handler) {
        this.nextHanlder = handler;
    }
}
