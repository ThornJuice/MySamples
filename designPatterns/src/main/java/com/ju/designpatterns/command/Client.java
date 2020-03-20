package com.ju.designpatterns.command;

/**
 * 命令模式
 * 定义:将每一个请求封装成对象,使可以用不同的请求对客户进行参数化。是一种数据驱动的设计模式，它属于行为型模式。
 * 使用场景:认为是命令的地方都可以使用命令模式,例如电视遥控器
 * 优点： 1、降低了系统耦合度。 2、新的命令可以很容易添加到系统中去。
 * 缺点：导致系统具有过多的命令类
 */
public class Client {

    public static void main(String[] strings) {
        //调用命令者
        Invoker invoker = new Invoker();
        //实际接收者
        ConcreteReceiver receiver = new ConcreteReceiver();
        //命令1
        ConcreteCommand1 concreteCommand = new ConcreteCommand1(receiver);
        //命令2
        ConcreteCommand2 concreteCommand2 = new ConcreteCommand2(receiver);
        //执行命令1
        invoker.setCommand(concreteCommand);
        invoker.action();
        //执行命令2
        invoker.setCommand(concreteCommand2);
        invoker.action();
    }
}
