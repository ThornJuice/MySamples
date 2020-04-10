package com.hzy.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hzy.customview.doodleboard.SimpleDoodleBoard;

import com.hzy.customview.doodleboard.AdvancedDoodleBoard;

public class DoodleActivity extends AppCompatActivity implements View.OnClickListener {
    private SimpleDoodleBoard simpleDoodleBoard;
    private AdvancedDoodleBoard advancedDoodleBoard;
    private ImageView mPaint;
    private ImageView mEraser;
    private ImageView mClean;
    private ImageView mLast;
    private ImageView mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);
        initView();
        initEvent();
    }

    //初始化控件
    private void initView() {
        simpleDoodleBoard = findViewById(R.id.doodle_view);
        advancedDoodleBoard = findViewById(R.id.advanced_view);
        mPaint = findViewById(R.id.iv_paint);
        mEraser = findViewById(R.id.iv_eraser);
        mClean = findViewById(R.id.iv_clean);
        mLast = findViewById(R.id.iv_last);
        mNext = findViewById(R.id.iv_next);
    }

    //设置监听事件
    private void initEvent() {
        mPaint.setOnClickListener(this);
        mEraser.setOnClickListener(this);
        mClean.setOnClickListener(this);
        mLast.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    //实现监听事件效果
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_paint:

                break;
            case R.id.iv_eraser:

                break;
            case R.id.iv_clean:
               // advancedDoodleBoard.clearAll();
                break;
            case R.id.iv_last:

                break;
            case R.id.iv_next:
                Toast.makeText(getApplicationContext(),"DoodleActivity",0).show();
                break;
        }
    }
}
