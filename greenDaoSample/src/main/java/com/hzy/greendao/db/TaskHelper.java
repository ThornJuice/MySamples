package com.hzy.greendao.db;


import com.hzy.greendao.Task;

import org.greenrobot.greendao.AbstractDao;

public class TaskHelper extends BaseDbHelper<Task, Long>{
    public TaskHelper(AbstractDao dao) {
        super(dao);
    }
}
