package com.ju.designpatterns.template;

import com.ju.designpatterns.factory.AbsHumanFactory;
import com.ju.designpatterns.factory.HumanFactory;
import com.ju.designpatterns.factory.StaticHumanFactory;
import com.ju.designpatterns.factory.WhiteHuman;
import com.ju.designpatterns.factory.YellowHuman;

/**
 * 模板模式
 * 定义:定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤,属于行为型模式
 * 使用场景:1、有多个子类共有的方法，且逻辑相同。 2、重要的、复杂的方法，可以考虑作为模板方法。
 * 优点: 1、封装不变部分，扩展可变部分。 2、提取公共代码，便于维护。 3、行为由父类控制，子类实现。
 * 缺点:每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大。
 */
public class Client {
    public static void main(String[] strings) {
        ConcreteClass1 concreteClass1 = new ConcreteClass1();
        concreteClass1.templateMethod();
        ConcreteClass2 concreteClass2 = new ConcreteClass2();
        concreteClass2.templateMethod();
    }
}