package com.hzy.customviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

public class MyViewPager extends ViewGroup {
    private Context mContext;
    private int[] images = {R.mipmap.image1, R.mipmap.image2,R.mipmap.image1};
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private int position;

    private int scrollX;
    private int startX;
    private int startY;

    public MyViewPager(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }

    private void init() {
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(images[i]);
            addView(imageView);
        }
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        mScroller = new Scroller(getContext());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递手势识别器
        mGestureDetector.onTouchEvent(event);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ACTION_MOVE", "scrollX=" + getScrollX());
                scrollX = getScrollX();//相对于初始位置滑动的距离
                //你滑动的距离加上屏幕的一半，除以屏幕宽度，就是当前图片显示的pos.如果你滑动距离超过了屏幕的一半，这个pos就加1
                position = (getScrollX() + getWidth() / 2) / getWidth();
                //滑到最后一张的时候，不能出边界
                if (position >= images.length) {
                    position = images.length - 1;
                }
                if (position < 0) {
                    position = 0;
                }
                if (mOnPageScrollListener != null) {
                    mOnPageScrollListener.onPageScrolled((float) (getScrollX() * 1.0 / (getWidth())), position);
                }
                break;
            case MotionEvent.ACTION_UP:
                mScroller.startScroll(scrollX, 0, -(scrollX - position * getWidth()), 0);
                Log.e("ACTION_UP", "scrollX=" + (-(scrollX - position * getWidth())));
                invalidate();
                if (mOnPageScrollListener != null) {
                    mOnPageScrollListener.onPageSelected(position);
                }
                break;
        }
        return true;
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            Log.e("CurrX", "mScroller.getCurrX()=" + mScroller.getCurrX());
            postInvalidate();
            if (mOnPageScrollListener != null) {
                Log.e("TAG", "offset=" + (float) (getScrollX() * 1.0 / (getWidth())));
                mOnPageScrollListener.onPageScrolled((float) (mScroller.getCurrX() * 1.0 / ((1) * getWidth())), position);
            }
        }
    }
    public interface OnPageScrollListener {
        /**
         * @param offsetPercent offsetPercent：getScrollX滑动的距离占屏幕宽度的百分比
         * @param position
         */
        void onPageScrolled(float offsetPercent, int position);

        void onPageSelected(int position);
    }

    private OnPageScrollListener mOnPageScrollListener;

    public void setOnPageScrollListener(OnPageScrollListener onPageScrollListener) {
        this.mOnPageScrollListener = onPageScrollListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       // return super.onInterceptTouchEvent(ev);
        // 如果左右滑动, 就需要拦截, 上下滑动,不需要拦截
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                mGestureDetector.onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int dx = endX - startX;
                int dy = endY - startY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    // 左右滑动
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
