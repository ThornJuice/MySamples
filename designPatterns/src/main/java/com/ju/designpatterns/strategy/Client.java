package com.ju.designpatterns.strategy;


/**
 * 策略模式
 * 定义:一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
 * 使用场景: LinearLayoutManage、GridLayoutManage每种方式都是一种策略
 * 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 * 优点: 1、算法可以自由切换。 2、避免使用多重条件判断。 3、扩展性良好。
 * 缺点: 1、策略类会增多。 2、所有策略类都需要对外暴露。
 */
public class Client {
    public static void main(String[] args) {
        Operation operation = new Operation(new OperateAdd());
        operation.execute(10, 5);
        Operation operation1 = new Operation(new OperateSubtract());
        operation1.execute(10, 5);
        Operation operation2 = new Operation(new OperateMultiply());
        operation2.execute(10, 5);

    }
}

