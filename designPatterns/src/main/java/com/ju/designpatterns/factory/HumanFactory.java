package com.ju.designpatterns.factory;

public class HumanFactory extends AbsHumanFactory {

    @Override
    public <T extends Human> T createHuman(Class<T> tClass) {
        Human human = null;
        try {
            human = (T) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) human;
    }
}
