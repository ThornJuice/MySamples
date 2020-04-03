package com.hzy.customview.layoutmanager;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 填充,滑动,回收
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {
    private final String TAG = CustomLayoutManager.class.getSimpleName();


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //    1 在RecyclerView初始化时，会被调用两次。
//    2 在调用adapter.notifyDataSetChanged()时，会被调用。
//    3 在调用setAdapter替换Adapter时,会被调用。
//    4 在RecyclerView执行动画时，它也会被调用。
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(TAG,"onLayoutChildren ");
        if (getItemCount() == 0){
            detachAndScrapAttachedViews(recycler);
            return;
        }
        //state.isPreLayout()是支持动画的
        if (getItemCount() == 0 && state.isPreLayout()){
            return;
        }
        //将当前Recycler中的view全部移除并放到报废缓存里,之后优先重用缓存里的view
        detachAndScrapAttachedViews(recycler);

        int actualHeight = 0;
        for (int i = 0 ;i < getItemCount() ; i++){
            View scrap = recycler.getViewForPosition(i);
            addView(scrap);
            measureChildWithMargins(scrap,0,0);
            int width = getDecoratedMeasuredWidth(scrap);
            int height = getDecoratedMeasuredHeight(scrap);
            layoutDecorated(scrap,0,actualHeight,width,actualHeight+height);
            actualHeight+=height;
            //超出界面的就不画了,也不add了
            if (actualHeight > getHeight()){
                break;
            }
        }
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }


    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d("feifeifei","getChildCount() " + getChildCount() + " recycler.getScrapList().size() " + recycler.getScrapList().size());
        Log.e(TAG,"dy:  " +dy);
        //界面向下滚动的时候,dy为正,向上滚动的时候dy为负

        //向下滚动的时候,最下面的值不能超过总值,
        //向上滚动的时候,最上面的值不能小于0
        int canScroll = dy;
        if (dy>0){
            View  lastView = getChildAt(getChildCount() -1);
            int lastPos = getPosition(lastView);
            if (lastPos >= getItemCount()-1){
                if (lastView.getBottom() - dy < getHeight()){
                    canScroll = lastView.getBottom() - getHeight();
                    offsetChildrenVertical(canScroll*-1);
                    return 0;
                }
            }
        }else {
            View  firView = getChildAt(0);
            int firstPos = getPosition(firView);
            if (firstPos <= 0){
                if (firView.getTop() - dy >= 0){
                    canScroll = firView.getTop();
                    offsetChildrenVertical(canScroll*-1);
                    return 0;
                }
            }
        }

        //底部填充
        fill(dy,recycler,state);
        //滚动
        offsetChildrenVertical(dy*-1);

        //回收已经离开界面的
        recycleOut(dy,recycler,state);


        return dy;
    }

    private void fill(int dy, RecyclerView.Recycler recycler, RecyclerView.State state){
        //向下滚动
        if (dy > 0){
            //先在底部填充
            View  lastView = getChildAt(getChildCount() -1);
            int lastPos = getPosition(lastView);
            if (lastPos == getChildCount()-1){
                return;
            }
            Log.d("feifeifei","lastView top" + lastView.getTop() + " bottom " + lastView.getBottom());
            if (lastView.getBottom() -  dy < getHeight()){
                View scrap = recycler.getViewForPosition(lastPos+1);
                addView(scrap);
                measureChildWithMargins(scrap,0,0);
                int width = getDecoratedMeasuredWidth(scrap);
                int height = getDecoratedMeasuredHeight(scrap);
                layoutDecorated(scrap,0,lastView.getBottom(),width,lastView.getBottom()+height);
//                bottomItemPos++;
            }
        }else {
            //向上滚动
            //现在顶部填充
            View firstView = getChildAt(0);
            Log.d("feifeifei","firstView top" + firstView.getTop() + " bottom " + firstView.getBottom());
            int layoutPostion = getPosition(firstView);
            if (layoutPostion == 0){
                return;
            }
            if (firstView.getTop() >= 0 ){
                View scrap = recycler.getViewForPosition(layoutPostion -1);
                addView(scrap,0);
                measureChildWithMargins(scrap,0,0);
                int width = getDecoratedMeasuredWidth(scrap);
                int height = getDecoratedMeasuredHeight(scrap);
                layoutDecorated(scrap,0,firstView.getTop() - height,width,firstView.getTop());
            }
        }
    }

    private void recycleOut(int dy, RecyclerView.Recycler recycler, RecyclerView.State state){
        for (int i = 0 ; i <getChildCount() ;i++){
            View view = getChildAt(i);

            //            Log.d("feifeifei","recycleOut position "+ i + " getDecoratedTop " + getDecoratedTop(view) + " getDecoratedBottom " + getDecoratedBottom(view));
            Log.d("feifeifei","recycleOut position "+ i + " top " + view.getTop() + " bottom " + view.getBottom());

            if (dy >0){
                if (view.getBottom()-dy <0){
                    Log.d("feifeifei","recycleOut " + i);
                    removeAndRecycleView(view,recycler);
                }
            }else {
                if (view.getTop()-dy > getHeight()){
                    Log.d("feifeifei","recycleOut " + i);
                    removeAndRecycleView(view,recycler);
                }
            }
        }
    }
}