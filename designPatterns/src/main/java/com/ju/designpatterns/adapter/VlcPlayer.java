package com.ju.designpatterns.adapter;

public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc() {
        System.out.println("Start to play vlc");
    }

    @Override
    public void playMp4() {

    }
}
