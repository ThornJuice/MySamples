package com.example.administrator.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myapplication.db.DbCore;
import com.example.administrator.myapplication.greendao.DaoMaster;
import com.example.administrator.myapplication.greendao.DaoSession;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        DbCore.init(this);
        DbCore.enableQueryBuilderLog(); //开启调试 log
        SQLiteStudioService.instance().start(this);

    }
}
