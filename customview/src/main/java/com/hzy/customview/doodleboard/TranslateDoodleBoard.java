package com.hzy.customview.doodleboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TranslateDoodleBoard extends View {
    private final static String TAG = "TranslateDoodleBoard";

    private Paint mPaint = new Paint();
    private List<PathItem> mPathList = new ArrayList<>(); // 保存涂鸦轨迹的集合
    private TouchGestureDetector mTouchGestureDetector; // 触摸手势监听
    private float mLastX, mLastY;
    private PathItem mCurrentPathItem; // 当前的涂鸦轨迹
    private PathItem mSelectedPathItem; // 选中的涂鸦轨迹

    public TranslateDoodleBoard(Context context) {
        super(context);

    }

    public TranslateDoodleBoard(Context context, @Nullable AttributeSet attrs) {
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

        mTouchGestureDetector = new TouchGestureDetector(getContext(), new TouchGestureDetector.OnTouchGestureListener() {
            RectF bounds = new RectF();

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                boolean isSelected = false;
                for (int i = 0; i < mPathList.size(); i++) {
                    PathItem pathItem = mPathList.get(i);
                    pathItem.mPath.computeBounds(bounds, true);
                    bounds.offset(pathItem.mX, pathItem.mY);
                    if (bounds.contains(e.getX(), e.getY())) {
                        mSelectedPathItem = pathItem;
                        isSelected = true;
                        break;
                    }
//                    if(touchInPath(pathItem.mPath,(int)e.getX(),(int)e.getY())){
//                        mSelectedPathItem = pathItem;
//                        isSelected = true;
//                        break;
//                    }
                }
                if (!isSelected) {
                    mSelectedPathItem = null;
                }
                invalidate();
                return true;
            }

            @Override
            public void onScrollBegin(MotionEvent e) { // 滑动开始
                Log.d(TAG, "onScrollBegin: ");
                if (mSelectedPathItem == null) {
                    mCurrentPathItem = new PathItem(); // 新的涂鸦
                    mPathList.add(mCurrentPathItem); // 添加的集合中
                    mCurrentPathItem.mPath.moveTo(e.getX(), e.getY());
                    mLastX = e.getX();
                    mLastY = e.getY();
                }

                invalidate(); // 刷新
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { // 滑动中
                Log.d(TAG, "onScroll: " + e2.getX() + " " + e2.getY());
                if (mSelectedPathItem == null) {
                    mCurrentPathItem.mPath.quadTo(
                            mLastX,
                            mLastY,
                            (e2.getX() + mLastX) / 2,
                            (e2.getY() + mLastY) / 2); // 使用贝塞尔曲线 让涂鸦轨迹更圆滑
                    mLastX = e2.getX();
                    mLastY = e2.getY();
                } else {
                    mSelectedPathItem.mX = mSelectedPathItem.mX - distanceX;
                    mSelectedPathItem.mY = mSelectedPathItem.mY - distanceY;
                }
                invalidate(); // 刷新
                return true;
            }

            @Override
            public void onScrollEnd(MotionEvent e) { // 滑动结束
                Log.d(TAG, "onScrollEnd: ");
                if (mSelectedPathItem == null) {
                    mCurrentPathItem.mPath.quadTo(
                            mLastX,
                            mLastY,
                            (e.getX() + mLastX) / 2,
                            (e.getY() + mLastY) / 2); // 使用贝塞尔曲线 让涂鸦轨迹更圆滑
                    mCurrentPathItem = null; // 轨迹结束
                }

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
        for (PathItem pathItem : mPathList) { // 绘制涂鸦轨迹
            canvas.save(); // 1.保存画布状态，下面要变换画布
            canvas.translate(pathItem.mX, pathItem.mY); // 根据涂鸦轨迹偏移值，偏移画布使其画在对应位置上
            Log.e(TAG, "mX:" + pathItem.mX + "mY:" + pathItem.mY);
            if (mSelectedPathItem == pathItem) {
                mPaint.setColor(Color.YELLOW); // 点中的为黄色
            } else {
                mPaint.setColor(Color.RED); // 其他为红色
            }
            canvas.drawPath(pathItem.mPath, mPaint);
            canvas.restore(); // 2.恢复画布状态，绘制完一个涂鸦轨迹后取消上面的画布变换，不影响下一个
        }
    }

    private static class PathItem {
        Path mPath = new Path();
        float mX, mY;
    }

    private boolean touchInPath(Path path, int x, int y) {
        RectF bounds = new RectF();
        path.computeBounds(bounds, true);
        Region region = new Region();
        region.setPath(path, new Region((int) bounds.left, (int) bounds.top, (int) bounds.right, (int) bounds.bottom));
        return region.contains(x, y);
    }
}
