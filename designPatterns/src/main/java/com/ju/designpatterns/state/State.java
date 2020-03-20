package com.ju.designpatterns.state;


public abstract class State {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    abstract void handle1();

    abstract void handle2();

}
