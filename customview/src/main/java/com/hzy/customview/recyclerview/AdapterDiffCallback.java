package com.hzy.customview.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * @author: wxj
 * @date: 2021/4/28
 * @description:
 */
public class AdapterDiffCallback extends DiffUtil.Callback {
    private List<User> oldList;
    private List<User> newList;

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    public AdapterDiffCallback(List<User> oldList, List<User> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        User oldData = oldList.get(oldItemPosition);
        User newData = newList.get(newItemPosition);
        return oldData.getId() == newData.getId() && oldData.getName().equals(newData.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        String oldNickname = oldList.get(oldItemPosition).getName();
        String newNickname = newList.get(oldItemPosition).getName();

        Bundle bundle = new Bundle();

        if (!oldNickname.equals(newNickname))
            bundle.putString("nickname", newNickname);
        if (bundle.size() == 0)
            return null;
        return bundle;
    }
}
