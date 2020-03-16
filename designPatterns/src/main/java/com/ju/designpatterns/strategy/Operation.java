package com.ju.designpatterns.strategy;

public class Operation {
    private Strategy strategy;
    public Operation(Strategy strategy){
        this.strategy =strategy;
    }
    public void execute(int num1, int num2) {
        System.out.println(strategy.operate(num1, num2));
    }
}
