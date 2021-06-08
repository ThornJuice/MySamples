package com.ju.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxj
 * @date: 2021/4/12
 * @description:
 */
public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<User> mList;
    private OnItemClickListener onItemClickListener;

    public void setAdapterOnclick(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public SimpleAdapter(Context context, List<User> list) {
        mContext = context;
        mList = new ArrayList<>();
        setData(list);
    }

    public void setData(List<User> data) {
        if (data != null) {
            mList.clear();
            mList.addAll(data);
        }

    }

    public List<User> getData() {
        return mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type1, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {
                    onItemClickListener.onClick(v, (int) v.getTag());
                }

            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).tv_name.setText(mList.get(position).getName());
        ((MyViewHolder) holder).tv_name.setTag(position);
    }


    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
