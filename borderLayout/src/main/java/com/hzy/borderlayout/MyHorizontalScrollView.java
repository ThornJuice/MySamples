package com.hzy.borderlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;


public class MyHorizontalScrollView extends HorizontalScrollView {
    //触摸前的点
    private float x;

    //手势抬起之后的点
    private float x1;

    private onScrollChangeListener onScrollChangeListener;

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface onScrollChangeListener {
        /**
         * 滚动监听
         *
         * @param scrollView
         * @param x
         * @param y
         */
        void onScrollChanged(HorizontalScrollView scrollView, int x, int y);

        /**
         * 滑动到最左侧
         *
         * @param scrollView
         */
        void onScrollFarLeft(HorizontalScrollView scrollView);

        /**
         * 滑动到最右侧
         *
         * @param scrollView
         */
        void onScrollFarRight(HorizontalScrollView scrollView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                x1 = ev.getX();
                if (computeHorizontalScrollOffset() == 0 && x-x1<0) {
                    //滑动最左边
                    if (onScrollChangeListener != null) {
                        onScrollChangeListener.onScrollFarLeft(this);
                    }
                } else if (computeHorizontalScrollRange() - computeHorizontalScrollOffset()
                        <= computeHorizontalScrollExtent() && x-x1>0) {
                    //滑动最右边
                    if (onScrollChangeListener != null) {
                        onScrollChangeListener.onScrollFarRight(this);
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 设置监听
     *
     * @param onScrollChangeListener
     */
    public void setOnScrollChangeListener(MyHorizontalScrollView.onScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //回调
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(this, l, t);
        }
    }

}
