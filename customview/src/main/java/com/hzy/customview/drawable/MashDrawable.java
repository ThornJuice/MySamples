package com.hzy.customview.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author: wxj
 * @date: 2021/4/14
 * @description:
 */
public class MashDrawable extends Drawable {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int INTERVAL = 200;
    {
        paint.setColor(Color.BLUE);
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 0; i < getBounds().right; i += INTERVAL) {
            for (int j = 0; j <getBounds().bottom ; j+= INTERVAL) {
                canvas.drawLine(getBounds().left,j,getBounds().right,j,paint);
                canvas.drawLine(i,getBounds().top,i,getBounds().bottom,paint);
            }
        }

    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public int getAlpha() {
        return paint.getAlpha();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha() == 0 ? PixelFormat.TRANSPARENT :
                paint.getAlpha() == 0xff ? PixelFormat.OPAQUE : PixelFormat.TRANSLUCENT;

    }
}
