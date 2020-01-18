package com.hzy.baselib.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

/**
 * 图片选择框架 PictureSelector的初始化配置
 */

public class PictureSelectorConfig {
    private static boolean isCompress = true;//是否压缩
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    /**
     * 初始化单张图片选择的配置
     *
     * @param activity activity使用
     */
    public static void choosePhoto(Activity activity, int resultCode, boolean isCut) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(activity)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                        .maxSelectNum(1)// 最大图片选择数量 int
//                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .previewVideo(false)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(isCut)// 是否裁剪 true or false
                        .compress(isCompress)// 是否压缩 true or false
//                .compressMaxKB(500)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
//                .compressWH(7, 10) // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
                        .glideOverride(130, 130)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                        .isGif(false)// 是否显示gif图片 true or false
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> lists
                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                        .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoSecond()// 显示多少秒以内的视频or音频也可适用 int
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化单张图片选择的配置
     *
     * @param fragment fragment使用
     */
    public static void choosePhoto(Fragment fragment, int resultCode, boolean isCut) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(fragment.getActivity(),
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(fragment.getActivity(), PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(fragment.getActivity())
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                        .maxSelectNum(1)// 最大图片选择数量 int
//                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .previewVideo(false)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                        .enableCrop(isCut)// 是否裁剪 true or false
                        .compress(isCompress)// 是否压缩 true or false
//                .compressMaxKB(500)//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
//                .compressWH(7, 10) // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
                        .glideOverride(130, 130)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                        .isGif(false)// 是否显示gif图片 true or false
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> lists
                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                        .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoSecond()// 显示多少秒以内的视频or音频也可适用 int
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 相册选择 所有类型 activity使用
     *
     * @param activity
     */
    public static void chooseAllType(Activity activity, int resultCode) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(activity)
                        .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                        .maxSelectNum(1)// 最大图片选择数量 int
//                        .minSelectNum(0)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(false)// 是否可预览图片 true or false
                        .previewVideo(true)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .isCamera(false)// 是否显示拍照按钮 true or false
                        .compress(isCompress)// 是否压缩 true or false
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .glideOverride(130, 130)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
//                        .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> lists
                        .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoMaxSecond(10)
//                .videoMinSecond(5)
//                .videoSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .enableCrop(false)// 是否裁剪
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 相册选择 所有类型  fragment使用
     *
     * @param fragment
     */
    public static void chooseAllType(Fragment fragment, int resultCode) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(fragment.getContext(),
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(fragment.getActivity(), PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(fragment)
                        .openGallery(PictureMimeType.ofAll())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
//                        .maxSelectNum(1)// 最大图片选择数量 int
//                        .minSelectNum(0)// 最小选择数量 int
                        .imageSpanCount(3)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(false)// 是否可预览图片 true or false
                        .previewVideo(true)// 是否可预览视频 true or false
                        .enablePreviewAudio(false) // 是否可播放音频 true or false
                        .isCamera(false)// 是否显示拍照按钮 true or false
                        .compress(isCompress)// 是否压缩 true or false
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .glideOverride(130, 130)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
//                        .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> lists
                        .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoMaxSecond(10)
//                .videoMinSecond(5)
//                .videoSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .enableCrop(false)// 是否裁剪
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 拍照  activity使用
     *
     * @param activity
     */
    public static void openCameraImg(Activity activity, int resultCode, boolean isCut) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                PictureSelector.create(activity)
                        .openCamera(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .enableCrop(isCut)// 是否裁剪
                        .compress(isCompress)// 是否压缩 true or false
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拍照 fragment使用
     *
     * @param fragment
     */
    public static void openCameraImg(Fragment fragment, int resultCode,boolean isCut) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(fragment.getContext(),
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(fragment.getActivity(), PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                PictureSelector.create(fragment)
                        .openCamera(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .enableCrop(isCut)// 是否裁剪
                        .compress(isCompress)// 是否压缩 true or false
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 录视频 activity使用
     *
     * @param activity
     */
    public static void openCameraVideo(Activity activity, int resultCode) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(activity)
                        .openCamera(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .enableCrop(false)// 是否裁剪
                        .compress(isCompress)// 是否压缩 true or false
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 录视频 fragment使用
     *
     * @param fragment
     */
    public static void openCameraVideo(Fragment fragment, int resultCode) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(fragment.getContext(),
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(fragment.getActivity(), PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(fragment)
                        .openCamera(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .recordVideoSecond(10)//视频秒数录制 默认60s int
                        .enableCrop(false)// 是否裁剪
                        .compress(isCompress)// 是否压缩 true or false
                        .forResult(resultCode);//结果回调onActivityResult code
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
