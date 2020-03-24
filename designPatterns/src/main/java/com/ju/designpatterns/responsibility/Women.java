package com.ju.designpatterns.responsibility;

public class Women implements IWomen {
    private int type;
    private String request;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getRequest() {
        return request;
    }

    public Women(int type, String request) {
        this.type = type;
        this.request = request;
    }
}
