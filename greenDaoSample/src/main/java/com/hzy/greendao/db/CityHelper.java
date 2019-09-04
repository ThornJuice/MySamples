package com.hzy.greendao.db;


import com.hzy.greendao.Task;

import org.greenrobot.greendao.AbstractDao;

public class CityHelper extends BaseDbHelper<Task, Long>{
    public CityHelper(AbstractDao dao) {
        super(dao);
    }
}
