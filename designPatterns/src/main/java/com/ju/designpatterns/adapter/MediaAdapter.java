package com.ju.designpatterns.adapter;


public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer player;

    @Override
    public void play(String type, String fileName) {
        if ("mp4".equalsIgnoreCase(type)) {
            player = new Mp4Player();
            player.playMp4();
        }
        if ("vlc".equalsIgnoreCase(type)) {
            player = new VlcPlayer();
            player.playVlc();
        }

    }
}
