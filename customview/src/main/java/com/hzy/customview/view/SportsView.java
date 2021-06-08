package com.hzy.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hzy.customview.R;

/**
 * @author: wxj
 * @date: 2021/4/13
 * @description:
 */
public class SportsView extends View {
    private Context context;
    private Paint paint;
    private final int RADIUS = 200;
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private TextPaint textPaint;
    private Camera camera;

    public SportsView(Context context) {
        super(context);
        this.context = context;
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    {

        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.getFontMetrics(fontMetrics);
        textPaint = new TextPaint();
        textPaint.setTextSize(50);
        camera = new Camera();
        camera.rotateX(30);
        camera.setLocation(0,0,-8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(20);
//        paint.setColor(ContextCompat.getColor(context, R.color.color_a9c6d6));
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
//
//        paint.setColor(ContextCompat.getColor(context, R.color.color_1));
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
//                    -90, 225, false, paint);
//        }
//        paint.setStyle(Paint.Style.FILL);
//        paint.setTextSize(60);
//        paint.setColor(ContextCompat.getColor(context, R.color.color_1));
        canvas.translate(200/2,200/2);
        camera.applyToCanvas(canvas);
        canvas.translate(-200/2,-200/2);
        canvas.drawBitmap(getBitmap(200), 0, 0, paint);

    }

    public Bitmap getBitmap(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.thelittleprince2, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.thelittleprince2, options);
    }
}
