package com.ju.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int i = 0;
    int num = 0;
    int count = 0;

    Context mContext;
    List<User> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setAdapterOnclick(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);

    }

    public MultiTypeAdapter(Context context, List<User> list) {
        mContext = context;
        setData(list);
    }

    public void setData(List<User> data) {
        mList.clear();
        mList.addAll(data);
    }

    public List<User> getData() {
        return mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("MyAdapter...", "onCreateViewHolder: " + i++);
        View view;
        if (0 == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type1, parent, false);
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
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type2, parent, false);
            final MyViewHolder2 viewHolder = new MyViewHolder2(view);
            viewHolder.iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(v, (int) v.getTag());
                    }

                }
            });
            return viewHolder;
        }
//        View view = View.inflate(mContext,R.layout.item_bar,null);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e("MyAdapter...", "onBindViewHolder: " + num++);

        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv_name.setText(mList.get(position).getName());
            ((MyViewHolder) holder).tv_name.setTag(position);
        } else if (holder instanceof MyViewHolder2) {
            ((MyViewHolder2) holder).iv_image.setTag(position);
        }
    }



    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        Log.e("MyAdapter...", "onViewAttachedToWindow: " + count++);
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

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView iv_image;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getItemType();
    }
}
