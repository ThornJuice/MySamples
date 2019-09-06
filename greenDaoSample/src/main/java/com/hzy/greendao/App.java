package com.hzy.greendao;

import android.app.Application;
import android.content.Context;
import android.util.Log;


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
        SQLiteStudioService.instance().start(this);
        copyDBToDatabases();
    }
    /**
     * assets目录下的db转移到databases
     */
    public void copyDBToDatabases() {
        try {
            String outFileName = Constant.DB_PATH + Constant.DB_CITY;
            File file = new File(Constant.DB_PATH);
            if (!file.mkdirs()) {
                file.mkdirs();
            }
            File dataFile = new File(outFileName);
            if (dataFile.exists()) {
                dataFile.delete();
            }
            InputStream myInput;
            myInput = getAssets().open(Constant.DB_CITY);
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
