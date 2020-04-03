package com.hzy.wan.test;

public class Client {
    public static void main(String[] args) {


        System.out.println( check(0,2));
    }

    public static boolean check(int all, int b) {
        int val = b == 0 ? 1 : (2 << (b - 1));
        return (all & val) == val;
    }
}
