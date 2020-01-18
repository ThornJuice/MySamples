package com.hzy.baselib.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import com.hzy.baselib.R;
import com.hzy.baselib.base.BaseActivity;
import com.hzy.baselib.util.MediaConstant;
import com.hzy.baselib.util.ServerInterface;
import com.hzy.baselib.util.ToastUtil;

import java.io.File;

public class ShowVideo extends BaseActivity {
    private VideoView videoView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_video;
    }

    @Override
    protected void initData() {
        String localUrl = getIntent().getStringExtra(MediaConstant.INSTANCE.getLOCAL_VIDEO());
        if (!TextUtils.isEmpty(localUrl)) {
            play(localUrl);
        } else {
            String imgUrl = getIntent().getStringExtra(MediaConstant.INSTANCE.getLINE_VIDEO());
            if (!TextUtils.isEmpty(imgUrl)) {
                play(imgUrl);
            }
        }
    }

    @Override
    protected void initView() {
        videoView = findViewById(R.id.videoView);
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
    }

    private void play(String localUrl) {
        String url = "";
        File file = new File(localUrl);
        if (file.isFile() && file.exists()) {
            url = localUrl;
        } else {
            url = ServerInterface.INSTANCE.getIp() + localUrl;
        }
        Uri uri = Uri.parse(url);
        //设置视频路径
        videoView.setVideoURI(uri);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始播放视频
                videoView.start();
            }
        });
        //videoView.pause();
        videoView.start();

//        PVAUtils.isPictureType(PVAUtils.fileToType(new File("")));
    }

    //播放本地视频
    public static void playLocalVideo(Context context, String localPath) {
        Intent intent = new Intent(context, ShowVideo.class);
        intent.putExtra(MediaConstant.INSTANCE.getLOCAL_VIDEO(), localPath);
        context.startActivity(intent);
    }

    //播放网络视频
    public static void playLineVideo(Context context, String imgUrl) {
        Intent intent = new Intent(context, ShowVideo.class);
        intent.putExtra(MediaConstant.INSTANCE.getLINE_VIDEO(), imgUrl);
        context.startActivity(intent);
    }


    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            ToastUtil.INSTANCE.showToast("播放结束");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }
}