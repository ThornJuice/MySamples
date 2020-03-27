package com.ju.designpatterns.bridge;

public class RedCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius +", x: " +x+", "+ y +"]");
    }

    @Override
    public void drawRectangle(int width, int height) {

    }


}
