package com.hzy.customview.recyclerview;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.hzy.customview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxj
 * @date: 2021/4/12
 * @description:
 */
public class DiffAdapter2 extends BaseQuickAdapter<User, BaseViewHolder> {

    List<User> mList = new ArrayList<>();
    public DiffAdapter2(@Nullable List<User> data) {
        super(R.layout.item_bar, data);
        mList = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_name, item.getName());
    }

    public  List<User> getOldList(){
        return mList;
    }

    public void  updateData(List<User> data){
        mList.clear();
        for (int i = 0; i <data.size() ; i++) {
            User user = new User();
            user.setId(data.get(i).getId());
            user.setName(data.get(i).getName());
            mList.add(user);
        }
    }
}
