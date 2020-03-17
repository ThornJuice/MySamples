package com.ju.designpatterns.observer;


/**
 * 观察者模式
 * 定义:定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。属于行为型模式
 * 使用场景:一个对象的改变将导致其他一个或多个对象也发生改变，而不知道具体有多少对象将发生改变，可以降低对象之间的耦合度。
 *          一个对象必须通知其他对象，而并不知道这些对象是谁。
 *          需要在系统中创建一个触发链，A对象的行为将影响B对象，B对象的行为将影响C对象……，可以使用观察者模式创建一种链式触发机制。
 * 注意事项：1.避免循环调用;2.如果处理时间长,采取异步方式
 * 优点:1、观察者和被观察者是抽象耦合的。 2、建立一套触发机制。
 * 缺点:1、如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。 2、如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。
 */
public class Client {
    public static void main(String[] args) {
        ConcreteObservable observable = new ConcreteObservable();
        observable.addObserver(new ConcreteObserver("观察者1号"));
        observable.addObserver(new ConcreteObserver("观察者2号"));

        observable.eat();
        observable.play();
    }
}

