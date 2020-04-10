package com.hzy.customview.doodleboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimpleDoodleBoard extends View {
    private final static String TAG = "SimpleDoodleView";

    private Paint mPaint = new Paint();
    private List<Path> mPathList = new ArrayList<>(); // 保存涂鸦轨迹的集合
    private TouchGestureDetector mTouchGestureDetector; // 触摸手势监听
    private float mLastX, mLastY;
    private Path mCurrentPath; // 当前的涂鸦轨迹

    public SimpleDoodleBoard(Context context) {
        super(context);

    }

    public SimpleDoodleBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 设置画笔
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        // 由手势识别器处理手势
        mTouchGestureDetector = new TouchGestureDetector(getContext(), new TouchGestureDetector.OnTouchGestureListener() {

            @Override
            public void onScrollBegin(MotionEvent e) { // 滑动开始
                Log.d(TAG, "onScrollBegin: ");
                mCurrentPath = new Path(); // 新的涂鸦
                mPathList.add(mCurrentPath); // 添加的集合中
                mCurrentPath.moveTo(e.getX(), e.getY());
                mLastX = e.getX();
                mLastY = e.getY();
                invalidate(); // 刷新
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { // 滑动中
                Log.d(TAG, "onScroll: " + e2.getX() + " " + e2.getY());
                mCurrentPath.quadTo(
                        mLastX,
                        mLastY,
                        (e2.getX() + mLastX) / 2,
                        (e2.getY() + mLastY) / 2); // 使用贝塞尔曲线 让涂鸦轨迹更圆滑
                mLastX = e2.getX();
                mLastY = e2.getY();
                invalidate(); // 刷新
                return true;
            }

            @Override
            public void onScrollEnd(MotionEvent e) { // 滑动结束
                Log.d(TAG, "onScrollEnd: ");
                mCurrentPath.quadTo(
                        mLastX,
                        mLastY,
                        (e.getX() + mLastX) / 2,
                        (e.getY() + mLastY) / 2); // 使用贝塞尔曲线 让涂鸦轨迹更圆滑
                mCurrentPath = null; // 轨迹结束
                invalidate(); // 刷新
            }

        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean consumed = mTouchGestureDetector.onTouchEvent(event); // 由手势识别器处理手势
        if (!consumed) {
            return super.dispatchTouchEvent(event);
        }
        //return mTouchGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Path path : mPathList) { // 绘制涂鸦轨迹
            canvas.drawPath(path, mPaint);
        }
    }
}
