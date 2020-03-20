package com.hzy.smartfield.test.thread;

public class Main {
    public static  void main(String[] strings){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(this){
                    System.out.println("xxxx");
                }
            }
        });
        t1.start();

    }
  static  class MyR implements MyRunnable{
      @Override
      public void run() {
          System.out.println("xxxx");
      }
  }
}
