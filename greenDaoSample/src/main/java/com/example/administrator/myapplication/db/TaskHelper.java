package com.example.administrator.myapplication.db;

import com.example.administrator.myapplication.Task;

import org.greenrobot.greendao.AbstractDao;

public class TaskHelper extends BaseDbHelper<Task, Long>{
    public TaskHelper(AbstractDao dao) {
        super(dao);
    }
}
