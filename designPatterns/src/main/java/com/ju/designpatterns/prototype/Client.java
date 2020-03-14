package com.ju.designpatterns.prototype;

/**
 * 原型模式
 * 定义:用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
 * 使用场景: 1、资源优化场景。 2、类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等。 3、性能和安全要求的场景。
 *          4、通过 new 产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。 5、一个对象多个修改者的场景。
 *          6、一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用。
 * 优点:1、性能提高。 2、逃避构造函数的约束。
 * 缺点:1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候。 2、必须实现 Cloneable 接口。
 */
public class Client{
    public static void main(String[] args) {
        Shape shape = new Shape();
        for (int i = 0; i < 100; i++) {
            Shape cloneShape = (Shape) shape.clone();
            cloneShape.setType("square");
            System.out.println("square"+i);
        }
    }
}
