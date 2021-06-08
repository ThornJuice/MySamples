package com.ju.designpatterns.test;

/**
 * @author: wxj
 * @date: 2021/4/29
 * @description:
 */
public class SynThread {
    int x;
    int y;

    public void testSync() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    count(i);
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();

    }

    public void count(int value) {
        synchronized (this) {
            x = value;
            try {
                Thread.sleep(500);
                y = value;
                if (x != y) {
                    System.out.println("x = " + x + ",y = " + y);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
