package com.hzy.greendao.db;

import android.database.sqlite.SQLiteDatabase;

import com.hzy.greendao.App;
import com.hzy.greendao.Constant;
import com.hzy.greendao.greendao.DaoMaster;
import com.hzy.greendao.greendao.DaoSession;

public class DbHelper {
    private static DbHelper mInstance;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private DbHelper(String db) {
        //初始化建议放在Application中进行
       // if (mInstance == null) {
            //创建数据库"info.db"
            DaoMaster.OpenHelper openHelper = new MyOpenHelper(App.getAppContext(), db);
            //获取可写数据库
            database = openHelper.getWritableDatabase();
            //获取数据库对象
            daoMaster = new DaoMaster(database);
            //获取Dao对象管理者
            daoSession = daoMaster.newSession();
       // }
    }
    public static DbHelper getInstance(String db) {
        mInstance = new DbHelper(db);
        return mInstance;
    }
  /*  public static DbHelper getInstance(String db) {
        if (mInstance == null) {
            //保证异步处理安全操作
            synchronized (DbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DbHelper(db);
                }
            }
        }
        return mInstance;
    }*/

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
