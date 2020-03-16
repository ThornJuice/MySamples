package com.ju.designpatterns.factory;

public abstract  class AbsHumanFactory {
    public abstract  <T extends  Human>  T createHuman(Class<T> tClass);
}
