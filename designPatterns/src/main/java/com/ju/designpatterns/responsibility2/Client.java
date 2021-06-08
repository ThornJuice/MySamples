package com.ju.designpatterns.responsibility2;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式
 * 定义:为请求创建了一个接收者对象的链，避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，并且沿着这条链传递请求，直到有对象处理它为止。
 * 使用场景: okhttp中的拦截器
 * 优点: 1、降低耦合度。它将请求的发送者和接收者解耦。 2、简化了对象。使得对象不需要知道链的结构。 3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。 4、增加新的请求处理类很方便。
 * 缺点: 1、责任链长时，影响性能；2、类似递归调用，调试不方便
 */
public class Client {

    public static void main(String[] strings) {
        String request = "我是一个玩偶组装请求：";

        List<MyInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new MyInterceptorOne());
        interceptors.add(new MyInterceptorTwo());
        interceptors.add(new MyInterceptorThree());
        RealInterceptorChain chain = new RealInterceptorChain(interceptors);

        String result = chain.proceed(request);
        System.out.println("最终结果 = " + result);
    }
}
