package com.ju.designpatterns.state;

public class OpeningState extends LiftState {
    public OpeningState(Context context) {
        super(context);
    }

    public OpeningState() {
    }

    @Override
    void open() {
        System.out.println("open");
    }

    @Override
    void close() {

    }

    @Override
    void run() {

    }

    @Override
    void stop() {

    }
}
