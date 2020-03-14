package com.ju.designpatterns.builder;

import java.util.LinkedList;
import java.util.List;

public class KFCBuilder implements SnacksBuilder, StapleBuilder {

    //订单
    private List<String> order = new LinkedList<>();

    @Override
    public void buildFrenchFries(Size size) {
        order.add((size == Size.LARGE ? "大" : size == Size.MID ? "中" : "小") + "薯条");
    }

    @Override
    public void buildCoke(Size size) {
        order.add((size == Size.LARGE ? "大" : size == Size.MID ? "中" : "小") + "杯可乐");
    }

    @Override
    public void buildHamburger(Taste taste) {
        order.add((taste == Taste.SPICY ? "香辣" : "劲脆") + "鸡腿堡");
    }

    @Override
    public void buildChickenRoll() {
        order.add("老北京鸡肉卷");
    }

    //获取订单
    public List<String> getOrder() {
        return order;
    }

    //展示订单
    public void showOrder() {
        for (String s : this.order) {
            System.out.println("|---" + s);
        }
    }
}
