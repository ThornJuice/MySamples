package com.ju.designpatterns.builder;


/**
 * 建造者模式
 * 定义:使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式
 * 使用场景:1、需要生成的对象具有复杂的内部结构。 2、需要生成的对象内部属性本身相互依赖。
 * 优点:1、建造者独立，易扩展。 2、便于控制细节风险。
 * 缺点:1、产品必须有共同点，范围有限制。 2、如内部变化复杂，会有很多的建造类。
 */
public class Client {
    public static void main(String[] args) {
        KFCBuilder builder = new KFCBuilder();
        builder.buildCoke(Size.LARGE);
        builder.buildFrenchFries(Size.SMALL);
        builder.buildHamburger(Taste.SPICY);
        builder.showOrder();
    }
}

