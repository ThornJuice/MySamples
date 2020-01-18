package com.hzy.baselib.widget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.hzy.baselib.R;
import com.luck.picture.lib.tools.ScreenUtils;


public class PictureVideoDialog extends Dialog implements View.OnClickListener {

    private Activity mContext;
    private ItemClickListener listener;
    private TextView fromAlbum;
    private TextView fromCamera;
    private TextView fromCameraVideo;
    private TextView cancel;

    public PictureVideoDialog(@NonNull Activity context) {
        super(context);
        this.mContext = context;
    }

    public PictureVideoDialog(@NonNull Activity context, int themeResId) {
        super(context, themeResId);//BottomDialogStyle
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.picture_dialog, null);
        //初始化控件
        fromAlbum = view.findViewById(R.id.from_album);
        fromCamera = view.findViewById(R.id.from_camera);
        fromCameraVideo = view.findViewById(R.id.from_cameraVideo);
        cancel = view.findViewById(R.id.cancel);
        fromAlbum.setOnClickListener(this);
        fromCamera.setOnClickListener(this);
        fromCameraVideo.setOnClickListener(this);
        cancel.setOnClickListener(this);
        //将布局设置给Dialog
        setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.95);
        lp.y = 10; //设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp); //将属性设置给窗体

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.from_album) {//相册
            dismiss();
            if (listener != null) {
                listener.photo();
            }
        } else if (i == R.id.from_camera) {//相机拍照
            dismiss();
            if (listener != null) {
                listener.camera();
            }
        } else if (i == R.id.from_cameraVideo) {//相机录视频
            dismiss();
            if (listener != null) {
                listener.cameraVideo();
            }
        } else if (i == R.id.cancel) {
            dismiss();
        }
    }


    public void SetItemClickListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
        showDialog();
    }

    private void showDialog() {
        show();
    }

    public interface ItemClickListener {
        void photo();

        void camera();

        void cameraVideo();
    }
}
