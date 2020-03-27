package com.ju.designpatterns.bridge;

/**
 * 桥接模式
 * 定义:是用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * 使用场景: 对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统，桥接模式尤为适用。
 * 优点:1、抽象和实现的分离。 2、优秀的扩展能力
 * 缺点:桥接模式的引入会增加系统的理解与设计难度
 */
public class Client {
    public static void main(String[] strings) {
        Shape shape1 = new Circle(100, 100, 10, new GreenCircle());
        shape1.draw();
        Shape shape2 = new Circle(100, 100, 10, new RedCircle());
        shape2.draw();
        Shape shape3 = new Rectangle(100, 100, new GreenRectangle());
        shape3.draw();
    }
}
