package com.hzy.borderlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class LeftColumnAdapter extends RecyclerView.Adapter<LeftColumnAdapter.LockViewHolder> {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 第一列数据
     */
    private ArrayList<String> mLockColumnDatas;

    public LeftColumnAdapter(Context mContext, ArrayList<String> mLockColumnDatas) {
        this.mContext = mContext;
        this.mLockColumnDatas = mLockColumnDatas;
    }

    @Override
    public LockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LockViewHolder holder = new LockViewHolder(LayoutInflater.from(mContext).inflate(R.layout.table_left_column_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(LockViewHolder holder, final int position) {
        TextView mTextView = holder.mTextView;
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int padding = DisplayUtil.dip2px(mContext,MyLockTableView.mPadding);
        textViewParams.setMargins(padding,padding,padding,padding);
        textViewParams.height = DisplayUtil.dip2px(mContext, MyLockTableView.mRowDataheight);
        textViewParams.width = DisplayUtil.dip2px(mContext, MyLockTableView.mFirstColumnWidth);
        mTextView.setLayoutParams(textViewParams);
        mTextView.setText(mLockColumnDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mLockColumnDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class LockViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public LockViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.lock_text);
        }
    }
}
