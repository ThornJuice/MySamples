package com.ju.designpatterns.strategy;

public class OperateSubtract implements Strategy {

    @Override
    public int operate(int num1, int num2) {
        return num1 - num2;
    }
}
