package com.hzy.borderlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;


public class MyTableViewAdapter extends RecyclerView.Adapter<MyTableViewAdapter.TableViewHolder> {
    private Context mContext;
    /**
     * 第一列数据
     */
    private ArrayList<String> mLockColumnDatas;
    /**
     * 表格数据
     */
    private ArrayList<ArrayList<String>> mTableDatas;
    private OnTableViewCreatedListener mOnTableViewCreatedListener;
    /**
     * 表格横向滚动监听事件
     */
    private MyLockTableView.OnTableViewListener mTableViewListener;

    public MyTableViewAdapter(Context context, ArrayList<String> mLockColumnDatas, ArrayList<ArrayList<String>> mTableDatas) {
        this.mContext = context;
        this.mLockColumnDatas = mLockColumnDatas;
        this.mTableDatas = mTableDatas;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TableViewHolder mTableViewHolder = new TableViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_lock_head_table_contentview, null));
        if (mOnTableViewCreatedListener != null) {
            mOnTableViewCreatedListener.onTableViewCreatedCompleted(mTableViewHolder.mScrollView);
        }
        return mTableViewHolder;
    }

    @Override
    public void onBindViewHolder(final TableViewHolder holder, int position) {
        RecyclerView mLockRecyclerView = holder.mLockRecyclerView;
        RecyclerView mMainRecyclerView = holder.mMainRecyclerView;
        mLockRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        //mLockRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        //mMainRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mLockRecyclerView.setAdapter(new LeftColumnAdapter(mContext, mLockColumnDatas));
        mMainRecyclerView.setAdapter(new MainColumnAdapter(mContext, mTableDatas));
    }

    /**
     * 表格创建完成回调
     */
    public interface OnTableViewCreatedListener {
        /**
         * 返回当前横向滚动视图给上个界面
         */
        void onTableViewCreatedCompleted(MyHorizontalScrollView mScrollView);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mLockRecyclerView;
        RecyclerView mMainRecyclerView;
        MyHorizontalScrollView mScrollView;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            mLockRecyclerView = (RecyclerView) itemView.findViewById(R.id.lock_recyclerview);
            mMainRecyclerView = (RecyclerView) itemView.findViewById(R.id.main_recyclerview);
            //解决滑动冲突，只保留最外层RecyclerView的下拉和上拉事件
            mLockRecyclerView.setFocusable(false);
            mMainRecyclerView.setFocusable(false);
            mScrollView = (MyHorizontalScrollView) itemView.findViewById(R.id.lockScrollView_parent);
            mScrollView.setOnScrollChangeListener(new MyHorizontalScrollView.onScrollChangeListener() {
                @Override
                public void onScrollChanged(HorizontalScrollView scrollView, int x, int y) {
                    if (mTableViewListener != null) {
                        mTableViewListener.onTableViewScrollChange(x, y);
                    }
                }

                @Override
                public void onScrollFarLeft(HorizontalScrollView scrollView) {

                }

                @Override
                public void onScrollFarRight(HorizontalScrollView scrollView) {

                }
            });
        }
    }

    public void setOnTableViewCreatedListener(OnTableViewCreatedListener mOnTableViewCreatedListener) {
        this.mOnTableViewCreatedListener = mOnTableViewCreatedListener;
    }

    public void setHorizontalScrollView(MyLockTableView.OnTableViewListener mTableViewListener) {
        this.mTableViewListener = mTableViewListener;
    }
}
