package com.hzy.greendao.db;

/**
 * 文 件 名: DbHelperUtil
 * 说   明:  获取表 Helper 的工具类
 */
public class DbHelperUtil {
    private static TaskHelper mTaskHelper;
    private static CityHelper cityHelper;
    public static TaskHelper getTaskHelper() {
        if (mTaskHelper == null) {
            mTaskHelper = new TaskHelper(DbCore.getDaoSession().getTaskDao());
        }
        return mTaskHelper;
    }
    public static CityHelper getCityHelper() {
        if (cityHelper == null) {
            cityHelper = new CityHelper(DbCore.getDaoSession().getCityDao());
        }
        return cityHelper;
    }
}

