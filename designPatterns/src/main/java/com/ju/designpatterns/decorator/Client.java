package com.ju.designpatterns.decorator;

/**
 * 装饰模式
 * 定义:动态地给一个对象添加一些额外的职责
 * 使用场景:需要扩展一个类的功能,或给一个类增加附加功能
 * 优点:动态增加与撤销,扩展性好,比继承灵活
 * 缺点:多层装饰复杂度高
 */
public class Client {
    public static void main(String[] args) {
//      普通方式
//        SchoolReport sugarReport = new SugarFourthGradeSchoolReport();
//        sugarReport.report();
//        sugarReport.sign();


        //使用装饰模式
        //被装饰的对象
        SchoolReport schoolReport = new FourthGradeSchoolReport();
        //第一次装饰
        schoolReport = new HighScoreDecotator(schoolReport);
        //第二次装饰
        schoolReport = new SortDecorator(schoolReport);
        schoolReport.report();
        schoolReport.sign();

    }
}
