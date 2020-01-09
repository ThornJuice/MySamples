package com.hzy.borderlayout;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAndroidNativeLightStatusBar(true);
        setStatusBarColor(R.color.white);
        mContentView = (LinearLayout) findViewById(R.id.contentView);
        //构造假数据
        ArrayList<ArrayList<String>> mTableDatas = new ArrayList<ArrayList<String>>();
        ArrayList<String> mfristData = new ArrayList<String>();
        mfristData.add("楼层\n工序");
        for (int i = 0; i < 25; i++) {
            mfristData.add("地下室及车库底板");
        }
        mTableDatas.add(mfristData);
        for (int i = 32; i >=-2 ; i--) {
            ArrayList<String> mRowDatas = new ArrayList<String>();
            mRowDatas.add(i+"");
            for (int j = 0; j < 25; j++) {
                mRowDatas.add(j+"");
            }
            mTableDatas.add(mRowDatas);
        }
        final MyLockTableView mMyLockTableView = new MyLockTableView(this, mContentView, mTableDatas);
        mMyLockTableView.show();
    }

    /**
     * 修改状态栏颜色
     *
     * @param colorId
     */
    protected void setStatusBarColor(@ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, colorId));
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改状态栏文字颜色
     *
     * @param isdark true 黑色  false白色
     */
    protected void setAndroidNativeLightStatusBar(boolean isdark) {
        try {
            View decor = getWindow().getDecorView();
            if (isdark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
