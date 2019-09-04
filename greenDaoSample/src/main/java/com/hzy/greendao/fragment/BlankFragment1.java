package com.hzy.greendao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzy.greendao.R;
import com.hzy.greendao.Task;
import com.hzy.greendao.adapter.TaskListAdapter;
import com.hzy.greendao.db.DbHelperUtil;
import com.hzy.greendao.db.TaskHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment {
    TaskHelper taskHelper;
    TaskListAdapter adapter;
    RecyclerView recyclerView;

    public BlankFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_fragment1, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.btn_query).setVisibility(View.GONE);
        view.findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskHelper = DbHelperUtil.getTaskHelper();
                List<Task> taskList = taskHelper.queryAll();

                adapter =new TaskListAdapter(getActivity(),taskList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
            }
        });
        initData();
        return view;
    }

    private void initData() {
        taskHelper = DbHelperUtil.getTaskHelper();
        List<Task> taskList = taskHelper.queryAll();

        adapter =new TaskListAdapter(getActivity(),taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String task) {
        if("1".equals(task)){
            taskHelper = DbHelperUtil.getTaskHelper();
            List<Task> taskList = taskHelper.queryAll();
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
