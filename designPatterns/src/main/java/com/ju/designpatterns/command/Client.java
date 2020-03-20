package com.ju.designpatterns.iterator;

/**
 * 迭代器模式
 * 定义:这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。迭代器模式属于行为型模式。
 * 使用场景:1、访问一个聚合对象的内容而无须暴露它的内部表示。 2、需要为聚合对象提供多种遍历方式。 3、为遍历不同的聚合结构提供一个统一的接口。
 * 优点： 1、它支持以不同的方式遍历一个聚合对象。 2、迭代器简化了聚合类。 3、在同一个聚合上可以有多个遍历。 4、在迭代器模式中，增加新的聚合类和迭代器类都很方便，无须修改原有代码。
 * 缺点：由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性。
 */
public class Client {

    public static void main(String[] strings) {
        RealContainer container =new RealContainer();
        for(Iterator iterator = container.getIterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }

    }
}
