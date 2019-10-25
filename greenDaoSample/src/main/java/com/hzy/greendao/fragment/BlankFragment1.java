package com.hzy.greendao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzy.greendao.Constant;
import com.hzy.greendao.R;
import com.hzy.greendao.ResultBean;
import com.hzy.greendao.Task;
import com.hzy.greendao.adapter.TaskListAdapter;
import com.hzy.greendao.db.DbHelper;
import com.hzy.greendao.greendao.ResultBeanDao;
import com.hzy.greendao.greendao.TaskDao;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class BlankFragment1 extends Fragment {
    TaskDao taskDao;
    TaskListAdapter adapter;
    RecyclerView recyclerView;
    ResultBeanDao resultBeanDao;
    public BlankFragment1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        initData();
        return view;
    }

    private void initData() {
        taskDao =  DbHelper.getInstance(Constant.DB_TASK).getDaoSession().getTaskDao();
        List<Task> taskList = taskDao.loadAll();

        adapter =new TaskListAdapter(getActivity(),taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        resultBeanDao = DbHelper.getInstance(Constant.DB_TASK).getDaoSession().getResultBeanDao();
        List<ResultBean> resultBeanList = resultBeanDao.loadAll();
        Log.e("resultBeanList","size"+resultBeanList.size());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String task) {
        if("1".equals(task)){
            taskDao =  DbHelper.getInstance(Constant.DB_TASK).getDaoSession().getTaskDao();
            List<Task> taskList = taskDao.loadAll();
            adapter =new TaskListAdapter(getActivity(),taskList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
