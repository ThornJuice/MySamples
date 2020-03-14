package com.ju.designpatterns.proxy;

public class ProxyGamePlayer implements IGamePlayer{
    GamePlayer gamePlayer;
    public ProxyGamePlayer( GamePlayer gamePlayer){
         this.gamePlayer = gamePlayer;
    }

    @Override
    public void login(String user, String password) {
        gamePlayer.login(user,password);
    }

    @Override
    public void killBoss() {
        gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        gamePlayer.upgrade();
    }
}
