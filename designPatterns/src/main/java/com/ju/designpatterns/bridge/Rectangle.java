package com.ju.designpatterns.bridge;

public class Rectangle extends Shape {
    private int width,height;

    public Rectangle(int width, int height, DrawAPI drawAPI) {
        super(drawAPI);
        this.width = width;
        this.height = height;
    }

    public void draw() {
        drawAPI.drawRectangle(width,height);
    }
}
