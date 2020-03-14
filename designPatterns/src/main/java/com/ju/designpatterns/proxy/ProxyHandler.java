package com.ju.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    //被代理的对象
    Object obj;

    public ProxyHandler(Object obj) {
        this.obj = obj;
    }

    //代理方法
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = method.invoke(obj, objects);
        if (method.getName().equalsIgnoreCase("login")) {
            System.out.println("your account is login ");
        }
        return result;
    }
}
