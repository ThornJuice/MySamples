package com.ju.designpatterns.state;

/**
 * 状态模式
 * 定义: 类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式
 * 使用场景:1、行为随状态改变而改变的场景。 2、条件、分支语句的代替者。
 * 优点:1、结构清晰，避免过多的条件判断语句；2、体现了开闭原则与单一职责原则；3、封装性好
 * 缺点:1、状态模式的使用必然会增加系统类和对象的个数。 2、状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱
 */
public class Client {
    public static void main(String[] strings) {

        Bike bike = new Bike();
        bike.gearDown();
        bike.gearUp();
        bike.gearUp();
        bike.gearUp();
        bike.gearDown();
        bike.gearDown();
        bike.gearDown();
    }
}
