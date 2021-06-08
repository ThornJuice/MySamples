package com.hzy.customview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author: wxj
 * @date: 2021/4/27
 * @description:
 */
public class BitmapUtil {
    public static Bitmap createBitmap(Resources resources,int res,int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        // inJustDecodeBounds为true，不返回bitmap，只返回这个bitmap的尺寸
        options.inJustDecodeBounds = true;
        // 从资源中读取(比较浪费资源，所以上面设置为true，只获取图片宽高)
        BitmapFactory.decodeResource(resources, R.drawable.thelittleprince2, options);
        // 再设置为false，最后要返回bitmap
        options.inJustDecodeBounds = false;
        // 根据缩放比例重新计算宽高
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(resources, R.drawable.thelittleprince2, options);

    }
}
