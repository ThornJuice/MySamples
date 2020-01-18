package com.hzy.baselib.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Describe: Picture  Video  Audio 判断工具类
 */
public class PVAUtils {

    public final static int TYPE_IMAGE = 1;
    public final static int TYPE_VIDEO = 2;
    public final static int TYPE_AUDIO = 3;

    public static int isPictureType(String pictureType) {
        switch (pictureType) {
            case "image/PNG":
            case "image/jpeg":
            case "image/JPEG":
            case "image/webp":
            case "image/WEBP":
            case "image/gif":
            case "image/bmp":
            case "image/GIF":
            case "imagex-ms-bmp":
                return TYPE_IMAGE;
            case "video/3gp":
            case "video/3gpp":
            case "video/3gpp2":
            case "video/avi":
            case "video/mp4":
            case "video/quicktime":
            case "video/x-msvideo":
            case "video/x-matroska":
            case "video/mpeg":
            case "video/webm":
            case "video/mp2ts":
                return TYPE_VIDEO;
            case "audio/mpeg":
            case "audio/x-ms-wma":
            case "audio/x-wav":
            case "audio/amr":
            case "audio/wav":
            case "audio/aac":
            case "audio/mp4":
            case "audio/quicktime":
            case "audio/lamr":
            case "audio/3gpp":
                return TYPE_AUDIO;
        }
        return PictureConfig.TYPE_IMAGE;
    }
//    public static int isPictureType(String pictureType) {
//        switch (pictureType) {
//            case "image/png":
//            case "image/PNG":
//            case "image/jpeg":
//            case "image/JPEG":
//            case "image/webp":
//            case "image/WEBP":
//            case "image/gif":
//            case "image/bmp":
//            case "image/GIF":
//            case "imagex-ms-bmp":
//                return TYPE_IMAGE;
//            case "video/3gp":
//            case "video/3gpp":
//            case "video/3gpp2":
//            case "video/avi":
//            case "video/mp4":
//            case "video/quicktime":
//            case "video/x-msvideo":
//            case "video/x-matroska":
//            case "video/mpeg":
//            case "video/webm":
//            case "video/mp2ts":
//                return TYPE_VIDEO;
//            case "audio/mpeg":
//            case "audio/x-ms-wma":
//            case "audio/x-wav":
//            case "audio/amr":
//            case "audio/wav":
//            case "audio/aac":
//            case "audio/mp4":
//            case "audio/quicktime":
//            case "audio/lamr":
//            case "audio/3gpp":
//                return TYPE_AUDIO;
//        }
//        return PictureConfig.TYPE_IMAGE;
//    }

    /**
     * 是否是gif
     *
     * @param pictureType
     * @return
     */
    public static boolean isGif(String pictureType) {
        switch (pictureType) {
            case "image/gif":
            case "image/GIF":
                return true;
        }
        return false;
    }

    /**
     * 是否是gif
     *
     * @param path
     * @return
     */
    public static boolean isImageGif(String path) {
        if (!TextUtils.isEmpty(path)) {
            int lastIndex = path.lastIndexOf(".");
            String pictureType = path.substring(lastIndex, path.length());
            return pictureType.startsWith(".gif")
                    || pictureType.startsWith(".GIF");
        }
        return false;
    }

    /**
     * 是否是视频
     *
     * @param pictureType
     * @return
     */
    public static boolean isVideo(String pictureType) {
        switch (pictureType) {
            case "video/3gp":
            case "video/3gpp":
            case "video/3gpp2":
            case "video/avi":
            case "video/mp4":
            case "video/quicktime":
            case "video/x-msvideo":
            case "video/x-matroska":
            case "video/mpeg":
            case "video/webm":
            case "video/mp2ts":
                return true;
        }
        return false;
    }

    /**
     * 是否是图片
     *
     * @param pictureType
     * @return
     */
    public static boolean isImage(String pictureType) {
        switch (pictureType) {
            case "image/png":
            case "image/PNG":
            case "image/jpeg":
            case "image/JPEG":
            case "image/webp":
            case "image/WEBP":
            case "image/gif":
            case "image/bmp":
            case "image/GIF":
            case "imagex-ms-bmp":
                return true;
        }
        return false;
    }

    /**
     * 是否是网络图片
     *
     * @param path
     * @return
     */
    public static boolean isHttp(String path) {
        if (!TextUtils.isEmpty(path)) {
            if (path.startsWith("http")
                    || path.startsWith("https")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断文件类型是图片还是视频
     *
     * @param file
     * @return
     */
    public static String fileToType(File file) {
        if (file != null) {
            String name = file.getName();
            if (name.endsWith(".mp4") || name.endsWith(".avi")
                    || name.endsWith(".3gpp") || name.endsWith(".3gp") || name.startsWith(".mov")) {
                return "video/mp4";
            } else if (name.endsWith(".PNG") || name.endsWith(".png") || name.endsWith(".jpeg")
                    || name.endsWith(".gif") || name.endsWith(".GIF") || name.endsWith(".jpg")
                    || name.endsWith(".webp") || name.endsWith(".WEBP") || name.endsWith(".JPEG")
                    || name.endsWith(".bmp")) {
                return "image/jpeg";
            }
        }
        return "image/jpeg";
    }

    /**
     * 是否是长图
     *
     * @param media
     * @return true 是 or false 不是
     */
    public static boolean isLongImg(LocalMedia media) {
        if (null != media) {
            int width = media.getWidth();
            int height = media.getHeight();
            int h = width * 3;
            return height > h;
        }
        return false;
    }

    /**
     * 获取图片后缀
     *
     * @param path
     * @return
     */
    public static String getLastImgType(String path) {
        try {
            int index = path.lastIndexOf(".");
            if (index > 0) {
                String imageType = path.substring(index, path.length());
                switch (imageType) {
                    case ".png":
                    case ".PNG":
                    case ".jpg":
                    case ".jpeg":
                    case ".JPEG":
                    case ".WEBP":
                    case ".bmp":
                    case ".BMP":
                    case ".webp":
                        return imageType;
                    default:
                        return ".png";
                }
            } else {
                return ".png";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ".png";
        }
    }

    public static String getFileLastType(String path) {
        int index = path.lastIndexOf(".");
        if (index > 0) {
            String imageType = path.substring(index, path.length());
            switch (imageType) {
                case ".png":
                case ".PNG":
                case ".jpg":
                case ".jpeg":
                case ".JPEG":
                case ".WEBP":
                case ".bmp":
                case ".BMP":
                case ".webp":
                    return "image/jpeg";
                case ".3gp":
                case ".3gpp":
                case ".3gpp2":
                case ".avi":
                case ".mp4":
                case ".quicktime":
                case ".x-msvideo":
                case ".x-matroska":
                case ".mpeg":
                case ".webm":
                case ".mp2ts":
                    return "video/3gp";
                default:
                    return "image/jpeg";
            }
        }
        return "image/jpeg";
    }

    //返回视频缩略图bitmap
    public static Bitmap videoMap(String videoUrl) {
//        fileUrl(videoUrl);
//        if (isVilde) {
//            MediaMetadataRetriever retr = new MediaMetadataRetriever();
//            retr.setDataSource(videoUrl, new HashMap<String, String>());
//            Bitmap bitmap = retr.getFrameAtTime();
//            return bitmap;
//        } else {
//            return null;
//        }
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(videoUrl, new HashMap<String, String>());
        Bitmap bitmap = retr.getFrameAtTime();
        return bitmap;
    }

    public static void fileUrl(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //需要在子线程中处理的逻辑
                if (getURLimage(url) != null) {
                    isVilde = true;
                } else {
                    isVilde = false;
                }
            }
        }).start();
    }

    //加载图片
    private static Bitmap getURLimage(String path) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            bitmap = null;
            e.printStackTrace();
        }
        return bitmap;
    }

    private static boolean isVilde;
}
