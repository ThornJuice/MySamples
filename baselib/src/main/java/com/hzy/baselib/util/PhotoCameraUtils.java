package com.hzy.baselib.util;


import android.app.Activity;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hzy.baselib.R;
import com.hzy.baselib.activity.ShowBigImg;
import com.hzy.baselib.activity.ShowVideo;
import com.hzy.baselib.adapter.PictureOrVideoListNewAdapter;
import com.hzy.baselib.adapter.PictureVideoShowAdapter;
import com.hzy.baselib.widget.PictureVideoDialog;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class PhotoCameraUtils {

    public Activity act;
    public Fragment fragment;
    private PictureVideoDialog pictureVideoDialog;

    public static PhotoCameraUtils init(Activity ctx) {
        PhotoCameraUtils photoCameraUtils = new PhotoCameraUtils();
        photoCameraUtils.pictureVideoDialog = new PictureVideoDialog(ctx, R.style.BottomDialogStyle);
        photoCameraUtils.act = ctx;
        return photoCameraUtils;
    }

    public static PhotoCameraUtils init(Fragment mFragment) {
        PhotoCameraUtils photoCameraUtils = new PhotoCameraUtils();
        photoCameraUtils.pictureVideoDialog = new PictureVideoDialog(mFragment.getActivity(), R.style.BottomDialogStyle);
        photoCameraUtils.fragment = mFragment;
        return photoCameraUtils;
    }

    /**
     * @param pVListAdapter
     * @param recyclerView
     * @param mediaList     fragment 里面操作
     *                      文件选择弹窗操作适配器
     * @param isCut      是否裁剪
     */
    public void chooseFromFragment(final PictureOrVideoListNewAdapter pVListAdapter, RecyclerView recyclerView,
                                   final List<LocalMedia> mediaList, boolean isCut) {
        recyclerView.setAdapter(pVListAdapter);
        pVListAdapter.setNewData(mediaList);
        pVListAdapter.notifyDataSetChanged();
        pVListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == pVListAdapter.getItemCount() - 1) {
                    pictureVideoDialog.SetItemClickListener(new PictureVideoDialog.ItemClickListener() {
                        @Override
                        public void photo() {
                            try {
                                if (mediaList.size() >= 6) {
                                    //  ToastUtils.myToast(act, "最多上传6张图片");
                                } else {
                                    PictureSelectorConfig.choosePhoto(fragment, PictureConfig.CHOOSE_REQUEST,isCut);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void camera() {
                            if (mediaList.size() >= 6) {
                                // ToastUtils.myToast(act, "最多上传6张图片");
                            } else {
                                PictureSelectorConfig.openCameraImg(fragment, PictureConfig.CHOOSE_REQUEST,isCut);
                            }
                        }

                        @Override
                        public void cameraVideo() {
                            if (mediaList.size() >= 6) {
                                //ToastUtils.myToast(act, "最多上传6张图片");
                            } else {
                                PictureSelectorConfig.openCameraVideo(fragment, PictureConfig.CHOOSE_REQUEST);
                            }
                        }
                    });
                } else if (ListUtils.listEmpty(mediaList)) {
                    if ("image/jpeg".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        if (mediaList.get(position).isCut()) {
                            ShowBigImg.Companion.build(fragment.getActivity(), mediaList.get(position).getCutPath());
                        } else if (mediaList.get(position).isCompressed()) {
                            ShowBigImg.Companion.build(fragment.getActivity(), mediaList.get(position).getCompressPath());
                        } else {
                            ShowBigImg.Companion.build(fragment.getActivity(), mediaList.get(position).getPath());
                        }
                    } else if ("video/3gp".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        ShowVideo.playLineVideo(fragment.getActivity(), mediaList.get(position).getPath());
                    }
                }
            }
        });
    }


    /**
     * @param pVListAdapter
     * @param recyclerView
     * @param mediaList     activity 里面操作
     *                      文件选择弹窗操作适配器
     * @param isCut         是否裁剪
     */
    public void chooseFromActivity(final PictureOrVideoListNewAdapter pVListAdapter,
                                   RecyclerView recyclerView, final List<LocalMedia> mediaList, boolean isCut) {
        recyclerView.setAdapter(pVListAdapter);
        pVListAdapter.setNewData(mediaList);
        pVListAdapter.notifyDataSetChanged();

        pVListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == pVListAdapter.getItemCount() - 1) {
                    pictureVideoDialog.SetItemClickListener(new PictureVideoDialog.ItemClickListener() {
                        @Override
                        public void photo() {
                            try {
                                if (mediaList.size() >= 6) {
                                    // ToastUtils.myToast(act, "最多上传6张图片");
                                } else {
                                    PictureSelectorConfig.choosePhoto(act, PictureConfig.CHOOSE_REQUEST, isCut);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void camera() {
                            if (mediaList.size() >= 6) {
                                //ToastUtils.myToast(act, "最多上传6张图片");
                            } else {
                                PictureSelectorConfig.openCameraImg(act, PictureConfig.CHOOSE_REQUEST,isCut);
                            }
                        }

                        @Override
                        public void cameraVideo() {
                            if (mediaList.size() >= 6) {
                                //ToastUtils.myToast(act, "最多上传6张图片");
                            } else {
                                PictureSelectorConfig.openCameraVideo(act, PictureConfig.CHOOSE_REQUEST);
                            }
                        }
                    });
                } else if (ListUtils.listEmpty(mediaList)) {
                    if ("image/jpeg".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        if (mediaList.get(position).isCut()) {
                            ShowBigImg.Companion.build(act, mediaList.get(position).getCutPath());
                        } else if (mediaList.get(position).isCompressed()) {
                            ShowBigImg.Companion.build(act, mediaList.get(position).getCompressPath());
                        } else {
                            ShowBigImg.Companion.build(act, mediaList.get(position).getPath());
                        }
                    } else if ("video/3gp".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        ShowVideo.playLineVideo(act, mediaList.get(position).getPath());
                    }
                }
            }

        });
    }

    /**
     * @param recyclerView
     * @param mediaList    展示图片视频
     *                     Activity 使用
     */
    public void showInActivity(PictureVideoShowAdapter pVShowAdapter, RecyclerView recyclerView, final List<LocalMedia> mediaList) {
        recyclerView.setAdapter(pVShowAdapter);
        pVShowAdapter.setNewData(mediaList);
        pVShowAdapter.notifyDataSetChanged();
        pVShowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ListUtils.listEmpty(mediaList)) {
                    if ("image/jpeg".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        if (mediaList.get(position).isCut()) {
                            ShowBigImg.Companion.build(act, mediaList.get(position).getCutPath());
                        } else if (mediaList.get(position).isCompressed()) {
                            ShowBigImg.Companion.build(act, mediaList.get(position).getCompressPath());
                        } else {
                            ShowBigImg.Companion.build(act, mediaList.get(position).getPath());
                        }
                    } else if ("video/3gp".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        ShowVideo.playLineVideo(act, mediaList.get(position).getPath());
                    }
                }
            }
        });
    }

    /**
     * @param recyclerView
     * @param mediaList    展示图片视频
     *                     Fragment 使用
     */
    public void showInFragment(PictureVideoShowAdapter pVShowAdapter, RecyclerView recyclerView, final List<LocalMedia> mediaList) {
        recyclerView.setAdapter(pVShowAdapter);
        pVShowAdapter.setNewData(mediaList);
        pVShowAdapter.notifyDataSetChanged();
        pVShowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ListUtils.listEmpty(mediaList)) {
                    if ("image/jpeg".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        if (mediaList.get(position).isCut()) {
                            ShowBigImg.Companion.build(fragment.getActivity(), mediaList.get(position).getCutPath());
                        } else if (mediaList.get(position).isCompressed()) {
                            ShowBigImg.Companion.build(fragment.getActivity(), mediaList.get(position).getCompressPath());
                        } else {
                            ShowBigImg.Companion.build(fragment.getActivity(), mediaList.get(position).getPath());
                        }
                    } else if ("video/3gp".equals(PVAUtils.getFileLastType(mediaList.get(position).getPath()))) {
                        ShowVideo.playLineVideo(fragment.getActivity(), mediaList.get(position).getPath());
                    }
                }
            }
        });
    }
}
