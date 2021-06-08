package com.hzy.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hzy.customview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author: wxj
 * @date: 2021/4/12
 * @description:
 */
public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.ViewHolder> {

    Context mContext;
    List<User> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setAdapterOnclick(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);

    }

    public DiffAdapter(Context context, List<User> list) {
        mContext = context;

        setData(list);
    }

    public void setData(List<User> data) {
        mList.clear();
        for (int i = 0; i <data.size() ; i++) {
            User user = new User();
            user.setId(data.get(i).getId());
            user.setName(data.get(i).getName());
            mList.add(user);
        }
    }

    public List<User> getData() {
        return mList;
    }

    @NonNull
    @Override
    public DiffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bar, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
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
    public void onBindViewHolder(@NonNull DiffAdapter.ViewHolder holder, int position) {

        holder.tv_name.setText(mList.get(position).getName());
        holder.tv_name.setTag(position);
    }
    @Override
    public void onBindViewHolder(@NonNull DiffAdapter.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty())
            onBindViewHolder(holder, position);
        else {
            Bundle bundle = (Bundle) payloads.get(0);
            for (String key : bundle.keySet()) {
                switch (key) {
                    case "nickname":
                        holder.tv_name.setText((CharSequence) bundle.get(key));
                        break;

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

}
