package com.ju.designpatterns.composite;

/**
 * 组合模式
 * 定义:又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次，这种类型的设计模式属于结构型模式。
 * 使用场景:部分、整体场景，如树形菜单，文件、文件夹的管理。
 * 优点: 1、高层模块调用简单。 2、节点自由增加。
 * 缺点:在使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒置原则。
 */
public class Client {
    public static void main(String[] args) {
        Employee ceo = new Employee("Big Brother", 500000);
        Employee manager = new Employee("Tom", 200000);
        Employee saler = new Employee("Jerry", 100000);
        Employee saler2 = new Employee("Snoopy", 100000);
        ceo.add(manager);
        manager.add(saler);
        manager.add(saler2);
        System.out.println(ceo);
        for (int i = 0; i < ceo.getChild().size(); i++) {
            System.out.println(ceo.getChild().get(i));
            for (int j = 0; j < manager.getChild().size(); j++) {
                System.out.println(manager.getChild().get(j));
            }
        }
    }
}
