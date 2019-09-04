package com.hzy.greendao;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.hzy.greendao.db.DbCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class App extends Application {
    private static App instance;
    private static Context appContext;
    public static App getAppInsatnce(){
        return instance;
    }
    public static Context getAppContext(){
        return appContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();
        //初始化数据库
        //DbCore.init(this);
        DbCore.init(this,"city.db");
        DbCore.enableQueryBuilderLog(); //开启调试 log
        SQLiteStudioService.instance().start(this);
        copyDBToDatabases();
    }
    /**
     * assets目录下的db转移到databases
     */
    private static final String DB_NAME ="city.db";
    private static final String DB_PATH = "/data/data/com.hzy.greendao/databases/";
    public void copyDBToDatabases() {
        try {
            String outFileName = DB_PATH + DB_NAME;
            File file = new File(DB_PATH);
            if (!file.mkdirs()) {
                file.mkdirs();
            }
            File dataFile = new File(outFileName);
            if (dataFile.exists()) {
                dataFile.delete();
            }
            InputStream myInput;
            myInput = App.getAppContext().getAssets().open(DB_NAME);
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            Log.e("greenDao", "error--->" + e.toString());
            e.printStackTrace();
        }

    }
}
