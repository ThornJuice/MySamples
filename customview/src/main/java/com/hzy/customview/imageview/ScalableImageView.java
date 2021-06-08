package com.hzy.customview.imageview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import androidx.core.view.GestureDetectorCompat;

import com.hzy.customview.BitmapUtil;
import com.hzy.customview.DensityUtil;
import com.hzy.customview.R;


/**
 * @author: wxj
 * @date: 2021/4/27
 * @description:
 */
public class ScalableImageView extends View {
    private GestureDetectorCompat gestureDetector;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    public static final int imageWidth = DensityUtil.dip2px(200);
    private float originalOffsetX;
    private float originOffsetY;
    private float offsetX;
    private float offsetY;
    private boolean big;
    private float scaleFraction;//0~1f
    private float SCALE_FACTOR = 1.5f;
    private float widthScale;
    private float heightScale;
    private float scale;
    private OverScroller scroller;
    private ObjectAnimator objectAnimator;

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getObjectAnimator() {
        if (objectAnimator == null) {
            objectAnimator = ObjectAnimator.ofFloat(this, "scaleFraction", 0, 1);
        }
        return objectAnimator;
    }

    public ScalableImageView(Context context) {
        super(context);
        init(context);
    }


    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth() - bitmap.getWidth()) / 2.0f;
        originOffsetY = (getHeight() - bitmap.getHeight()) / 2.0f;

//        bigScale = (float) getWidth() / bitmap.getWidth();
//        smallScale = (float) getHeight() / bitmap.getHeight();
        float bitmapScale = (float) bitmap.getWidth() / bitmap.getHeight();
        float viewScale = (float) getWidth() / getHeight();
        Log.e("TestCustomViewActivity", "bitmapScale = " + bitmapScale);
        Log.e("TestCustomViewActivity", "viewScale = " + viewScale);
        if (bitmapScale > viewScale) {
            widthScale = (float) getWidth() / bitmap.getWidth();
            heightScale = (float) getHeight() / bitmap.getHeight() * SCALE_FACTOR;
        } else {
            heightScale = (float) getWidth() / bitmap.getWidth();
            widthScale = (float) getHeight() / bitmap.getHeight() * SCALE_FACTOR;
        }
        Log.e("TestCustomViewActivity", "widthScale = " + widthScale);
        Log.e("TestCustomViewActivity", "heightScale = " + heightScale);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(offsetX, offsetY);
        scale = widthScale + (heightScale - widthScale) * scaleFraction;

        canvas.scale(scale, scale, getWidth() / 2.0f, getHeight() / 2.0f);
        canvas.drawBitmap(bitmap, originalOffsetX, originOffsetY, paint);

    }

    private void init(Context context) {
        bitmap = BitmapUtil.createBitmap(context.getResources(), R.drawable.thelittleprince2, imageWidth);
        scroller = new OverScroller(context);
        gestureDetector = new GestureDetectorCompat(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                // 必须返回true

                Log.e("TestCustomViewActivity", "onDown" + MotionEvent.ACTION_DOWN);
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

                Log.e("TestCustomViewActivity", "onShowPress" + e.getAction());
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                Log.e("TestCustomViewActivity", "onSingleTapUp" + e.getAction());
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.e("TestCustomViewActivity", "onScroll" + e1.getAction());

                if (big) {
                    offsetX -= distanceX;
                    offsetY -= distanceY;
                    invalidate();
                }

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.e("TestCustomViewActivity", "onLongPress" + e.getAction());
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // ⽤于滑动时迅速抬起时被调⽤，⽤于⽤户希望控件进⾏惯性滑动的场景
                Log.e("TestCustomViewActivity", "onFling" + e1.getAction());
                return false;
            }
        });
//        gestureDetector.setIsLongpressEnabled(false);//关闭长按
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //用于设置了双击时，判断点击事件
                Log.e("TestCustomViewActivity", "onSingleTapConfirmed" + e.getAction());
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.e("TestCustomViewActivity", "onDoubleTap" + e.getAction());
                big = !big;
                if (big) {
                    getObjectAnimator().start();
                } else {
                    getObjectAnimator().reverse();
                }
                invalidate();
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.e("TestCustomViewActivity", "onDoubleTapEvent" + e.getAction());
                return false;
            }
        });
    }
}
