package com.ju.designpatterns.test;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadDemo {

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void main(String[] strings) {
        new SynThread().testSync();
//        testThread2();

    }


    private static void testThread1() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {

                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();

    }

    private static void testThread2() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {

                }
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 2; i++) {
            executor.execute(runnable);
        }
        executor.shutdown();
    }

    static int x;
    static int y;

    private    void count(int value) {
        synchronized (this){
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
