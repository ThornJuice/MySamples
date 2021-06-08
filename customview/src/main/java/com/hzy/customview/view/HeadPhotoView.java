package com.hzy.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.hzy.customview.R;

/**
 * @author: wxj
 * @date: 2021/4/13
 * @description:
 */
public class HeadPhotoView extends View {

    private float imgWidth = 500;

    private Paint mPaint;
    private Bitmap mBitmap;
    // 图像混合模式
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    RectF savedArea = new RectF();

    public HeadPhotoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLUE);
        mBitmap = getAvatar((int) imgWidth);
        savedArea.set(0, 0, imgWidth, imgWidth);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        和save几乎一样，唯一区别相比save会把绘制内容挪到离屏缓冲去
        必须设置，否则PorterDuffXfermode无效
        离屏缓冲还可通过setLayerType(LAYER_TYPE_HARDWARE,null)设置:
        三个参数
            setLayerType(LAYER_TYPE_HARDWARE,null);         开启硬件加速外会额外有一个离屏缓冲，耗性能
            setLayerType(LAYER_TYPE_SOFTWARE,null);         关闭硬件加速
            setLayerType(LAYER_TYPE_NONE,null);             开启硬件加速
        官方详解用途：在开启动画之前通过setLayerType()开启离屏缓冲，动画结束时再关闭；
                      对自定义属性动画无用，只是自带的属性动画有用
             例： view.setLayerType(View.LAYER_TYPE_HARDWARE，null);
                  ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotationY",180);
                  animator.addListener(new AnimatorListenerAdapter(){
                        @Override
                        public void onAnimationEnd(Animator animation){
                            view.setLayerType(LAYER_TYPE_NONE,null)
                        }
                  });
                  animator.start();
         对比saveLayer()：
            saveLayer比较灵活，在绘制的过程中就可以加，官方建议少用，方法很重
            setLayerType（）相比saveLayer不灵活，而且是全局的，比如在此类中可在{}中添加setLayerType(LAYER_TYPE_HARDWARE,null)开启离屏缓冲，
                            这样就是全局开启离屏缓冲，也就意味着局限性，假设你在此类中做叠加绘制之前有其他绘制内容，就不能使用
         */

        int saved = canvas.saveLayer(savedArea, mPaint);

        // 绘制一个圆
        canvas.drawOval(0, 0, imgWidth, imgWidth, mPaint);
        // 设置图像混合模式，进行叠加绘制
        mPaint.setXfermode(xfermode);

        // 绘制bitmap
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mPaint.setXfermode(null);

        // 绘制完成后使用 restoreToCount 将离屏缓冲的绘制内容拿回来
        canvas.restoreToCount(saved);

    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // inJustDecodeBounds为true，不返回bitmap，只返回这个bitmap的尺寸
        options.inJustDecodeBounds = true;
        // 从资源中读取(比较浪费资源，所以上面设置为true，只获取图片宽高)
        BitmapFactory.decodeResource(getResources(), R.drawable.thelittleprince2, options);
        // 再设置为false，最后要返回bitmap
        options.inJustDecodeBounds = false;
        // 根据缩放比例重新计算宽高
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.thelittleprince2, options);
    }

}

