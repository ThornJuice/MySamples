package com.ju.designpatterns.state;


public abstract class LiftState {
    protected Context context;

    public LiftState(Context context) {
        this.context = context;
    }
    public LiftState(){

    }
    abstract void open();

    abstract void close();

    abstract void run();

    abstract void stop();
}
