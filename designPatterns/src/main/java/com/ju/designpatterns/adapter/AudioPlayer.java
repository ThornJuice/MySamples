package com.ju.designpatterns.adapter;

public class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String type, String fileName) {
        if ("mp3".equalsIgnoreCase(type)) {
            System.out.println("Start to play mp3");
        } else if ("mp4".equalsIgnoreCase(type) || "vlc".equalsIgnoreCase(type)) {
            new MediaAdapter().play(type, fileName);
        } else {
            System.out.println("Unsupported types");
        }
    }
}
