package com.ju.designpatterns.mediator;

/**
 * 中介者模式
 * 定义:用一个中介对象来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。属于行为型模式
 * 使用场景:1、系统中对象之间存在比较复杂的引用关系，导致它们之间的依赖关系结构混乱而且难以复用该对象。 2、想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。
 * 优点:1、降低了类的复杂度，将一对多转化成了一对一。 2、各个类之间的解耦。 3、符合迪米特原则。
 * 缺点:复杂,难以维护
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator concreteMediator = new ConcreteMediator();
        ConcreteSubject1 subject1 = new ConcreteSubject1(concreteMediator);
        subject1.doSomething();
    }
}
