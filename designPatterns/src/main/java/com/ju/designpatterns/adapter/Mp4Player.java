package com.ju.designpatterns.adapter;

public class Mp4Player implements  AdvancedMediaPlayer{
    @Override
    public void playVlc() {

    }

    @Override
    public void playMp4() {
        System.out.println("Start to play mp4");
    }
}
