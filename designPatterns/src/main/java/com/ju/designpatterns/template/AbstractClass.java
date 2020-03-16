package com.ju.designpatterns.template;

public abstract class AbstractClass {
    //基本方法
    public abstract void doSomething();

    //基本方法
    public abstract void doAnything();

    //模板方法
    public final void templateMethod() {
        /**
         * 调用基本方法
         * */
        doSomething();
        doAnything();
    }
}
