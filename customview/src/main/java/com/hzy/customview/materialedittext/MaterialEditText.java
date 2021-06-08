package com.hzy.customview.materialedittext;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

import com.hzy.customview.DensityUtil;
import com.hzy.customview.R;

/**
 * @author: wxj
 * @date: 2021/4/14
 * @description:
 */
public class MaterialEditText extends AppCompatEditText {
    private static final int TEXT_SIZE = DensityUtil.dip2px(12);
    private static final int TEXT_MARGIN = DensityUtil.dip2px(8);
    private static final int TEXT_V_OFFSET = DensityUtil.dip2px(22);
    private static final int TEXT_H_OFFSET = DensityUtil.dip2px(5);
    private static final int TEXT_ANIMATOR_OFFSET = DensityUtil.dip2px(16);
    private boolean floatingLabelShown;
    private boolean useFloatingLabel;
    private float floatingLabelFraction;
    private Rect backgroundPadding = new Rect();
    private ObjectAnimator showAnimator;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MaterialEditText(Context context) {
        super(context);
        init();
    }


    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        for (int i = 0; i <attrs.getAttributeCount() ; i++) {

            Log.e("attrs...",  attrs.getAttributeName(i)+" = "+attrs.getAttributeValue(i));

        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true);
        init();
    }

    private void init() {
        paint.setTextSize(TEXT_SIZE);
        onFloatingLabelChange();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        //hide
                        floatingLabelShown = false;
                        getShowAnimator().reverse();
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        //show
                        floatingLabelShown = true;
                        getShowAnimator().start();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    int index = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha((int) (0xff * floatingLabelFraction));
        float offset = TEXT_ANIMATOR_OFFSET * (1 - floatingLabelFraction);
        canvas.drawText(getHint().toString(), TEXT_H_OFFSET, TEXT_V_OFFSET + offset, paint);
        Log.e("ondraw...", "floatingLabelFraction = " + floatingLabelFraction);
    }


    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    public void setUseFloatLabel(boolean useFloatingLabel) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel;
            onFloatingLabelChange();
        }
    }

    public ObjectAnimator getShowAnimator() {
        if (showAnimator == null) {
            showAnimator = ObjectAnimator.ofFloat(MaterialEditText.this, "floatingLabelFraction", 1);
        }
        return showAnimator;
    }

    private void onFloatingLabelChange() {
        getBackground().getPadding(backgroundPadding);
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN, getPaddingRight(), getPaddingBottom());
        } else {
            setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());
        }
    }
}
