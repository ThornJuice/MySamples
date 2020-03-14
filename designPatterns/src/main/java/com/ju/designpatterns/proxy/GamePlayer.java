package com.ju.designpatterns.proxy;

public class GamePlayer implements  IGamePlayer {
    String name;
    public  GamePlayer(String name){
        this.name = name;
    }
    @Override
    public void login(String name, String password) {
        System.out.println(name+" is online");
    }

    @Override
    public void killBoss() {
        System.out.println(name+" is killing Boss ");
    }

    @Override
    public void upgrade() {
        System.out.println(name+" upgrade ");
    }
}
