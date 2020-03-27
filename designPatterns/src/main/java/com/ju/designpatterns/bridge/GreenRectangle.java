package com.ju.designpatterns.bridge;

public class GreenRectangle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {

    }

    @Override
    public void drawRectangle(int width,int height) {
        System.out.println("Drawing Rectangle[ color: green, width: "
                + width +", height" +height+"]");
    }
}
