package com.hzy.customview.viewgroup;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxj
 * @date: 2021/4/20
 * @description:
 */
public class TagLayout extends ViewGroup {
    List<Rect> childBounds = new ArrayList<>();

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int layoutHeight = 0;//TagLayout高度
        int layoutWith = 0;//TagLayout宽度
        int widthUsed = 0;//已经使用的宽度
        int heightUsed = 0;//已经使用的高度
        int lineMaxHeight = 0;//行最大高度
        int margin = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int specWithMode = MeasureSpec.getMode(widthMeasureSpec);
            int specWithSize = MeasureSpec.getSize(widthMeasureSpec);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();

            Log.e("taglayout...", "leftMargin = " + lp.leftMargin);
            Log.e("taglayout...", "rightMargin = " + lp.rightMargin);
            if ((specWithMode != MeasureSpec.UNSPECIFIED) && widthUsed + child.getMeasuredWidth()+ lp.leftMargin + lp.rightMargin > specWithSize) {
//                Log.e("taglayout...", "i = " + i + "   MeasuredWidth = " + child.getMeasuredWidth());
                widthUsed = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

//            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
//            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            Rect childBound;
            if (childBounds.size() <= i) {
                childBound = new Rect();
                childBounds.add(childBound);
            } else {
                childBound = childBounds.get(i);
            }

            childBound.set(widthUsed + lp.leftMargin, heightUsed + lp.topMargin, child.getMeasuredWidth() + lp.leftMargin + widthUsed, heightUsed + child.getMeasuredHeight() + lp.topMargin);

            widthUsed += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            layoutWith = Math.max(layoutWith, widthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);

        }

        layoutHeight = heightUsed + lineMaxHeight;
        setMeasuredDimension(layoutWith, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            Rect rect = childBounds.get(i);
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
