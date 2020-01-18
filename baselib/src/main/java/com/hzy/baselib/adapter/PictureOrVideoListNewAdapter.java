package com.hzy.baselib.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.hzy.baselib.R;
import com.hzy.baselib.util.GlideUtil;
import com.hzy.baselib.util.LogUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class PictureOrVideoListNewAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {

    private int[] ints = {R.mipmap.img_blue_add_file};

    private Context context;
    private List<LocalMedia> mediaList;

    public PictureOrVideoListNewAdapter(Context context) {
        super(R.layout.gv_filter_image);
        this.context = context;
    }


    public void setNewData(@Nullable List<LocalMedia> data) {
        if (mediaList == null) {
            mediaList = new ArrayList<>();
        }
        mediaList.clear();
        mediaList.addAll(data);
        mediaList.add(new LocalMedia());
        super.setNewData(mediaList);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMedia media) {

        helper.addOnClickListener(R.id.ll_del);
        if (helper.getLayoutPosition() == getItemCount() - 1) {
            GlideUtil.INSTANCE.displayImage(ints[0], (ImageView) helper.getView(R.id.fiv));
            helper.setVisible(R.id.ll_del, false);
            helper.getView(R.id.ll_play_img).setVisibility(View.GONE);
        } else {
            helper.setVisible(R.id.ll_del, true);
            int mimeType = 0;
            try {
                mimeType = media.getMimeType();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String path = "";
            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            // 图片
            try {
                LogUtils.INSTANCE.i("原图地址::", media.getPath());
                LogUtils.INSTANCE.i("原图剪切地址::", media.getCutPath());
                LogUtils.INSTANCE.i("原图压缩地址::", media.getCompressPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int pictureType = PictureMimeType.isPictureType(media.getPictureType());
            if (pictureType == PictureConfig.TYPE_AUDIO) {
                helper.getView(R.id.ll_play_img).setVisibility(View.VISIBLE);
            } else if (pictureType == PictureConfig.TYPE_VIDEO) {
                helper.getView(R.id.ll_play_img).setVisibility(View.VISIBLE);
            } else if (pictureType == PictureConfig.TYPE_IMAGE) {
                helper.getView(R.id.ll_play_img).setVisibility(View.GONE);
            }

            if (mimeType == PictureMimeType.ofAudio()) {
                helper.setImageResource(R.id.fiv, R.drawable.audio_placeholder);
            } else {
                try {
                    GlideUtil.INSTANCE.displayImage(path, (ImageView) helper.getView(R.id.fiv));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
