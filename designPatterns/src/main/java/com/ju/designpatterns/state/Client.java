package com.ju.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 * 定义:为其他对象提供一种代理以控制对这个对象的访问,属于结构型模式
 * 使用场景 1、远程代理。 2、虚拟代理。 3、Copy-on-Write 代理。 4、保护（Protect or Access）代理。 5、Cache代理。 6、防火墙（Firewall）代理。 7、同步化（Synchronization）代理。 8、智能引用（Smart Reference）代理。
 * 优点:1、职责清晰。 2、高扩展性。 3、智能化。
 * 缺点:1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。 2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
 */
public class Client {
    public static void main(String[] strings) {
//  普通方式
//        IGamePlayer gamePlayer = new GamePlayer("Tom");
//        gamePlayer.login("Tom", "123");
//        gamePlayer.killBoss();
//        gamePlayer.upgrade();
//  静态代理
//        IGamePlayer proxy = new ProxyGamePlayer(new GamePlayer("Tom"));
//        proxy.login("Tom", "123");
//        proxy.killBoss();
//        proxy.upgrade();
//  动态代理,用于日志 事务 权限
        IGamePlayer player = new  GamePlayer("Tom");
        InvocationHandler handler  = new ProxyHandler(player);
        ClassLoader classLoader = player.getClass().getClassLoader();
        Class<?>[] cls = player.getClass().getInterfaces();
        IGamePlayer dynamicProxy = (IGamePlayer) Proxy.newProxyInstance(classLoader,cls,handler);
        dynamicProxy.login("Tom", "123");
        dynamicProxy.killBoss();
        dynamicProxy.upgrade();
    }
}
