package com.example.administrator.myapplication.db;

/**
 * 文 件 名: DbHelperUtil
 * 说   明:  获取表 Helper 的工具类
 */
public class DbHelperUtil {
    private static TaskHelper mTaskHelper;

    public static TaskHelper getTaskHelper() {
        if (mTaskHelper == null) {
            mTaskHelper = new TaskHelper(DbCore.getDaoSession().getTaskDao());
        }
        return mTaskHelper;
    }
}

