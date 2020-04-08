package com.hzy.customview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hzy.customview.drawboard.DrawMode;
import com.hzy.customview.drawboard.DrawingBoard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingBoard mDrawingBoard;
    private ImageView mPaint;
    private ImageView mEraser;
    private ImageView mClean;
    private ImageView mLast;
    private ImageView mNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    //初始化控件
    private void initView(){
        mDrawingBoard = findViewById(R.id.draw_board);
        mPaint = findViewById(R.id.iv_paint);
        mEraser = findViewById(R.id.iv_eraser);
        mClean = findViewById(R.id.iv_clean);
        mLast = findViewById(R.id.iv_last);
        mNext = findViewById(R.id.iv_next);
    }
    //设置监听事件
    private void initEvent(){
        //设置默认选择背景,level值为1
        //mPaint.getDrawable().setLevel(1);
     //  mPaint.getBackground().setLevel(1);
        mPaint.setOnClickListener(this);
        mEraser.setOnClickListener(this);
        mClean.setOnClickListener(this);
        mLast.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    //实现监听事件效果
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_paint:
                if (mDrawingBoard.getMode() != DrawMode.PaintMode) {
                    mDrawingBoard.setMode(DrawMode.PaintMode);
                }
              //  mPaint.getDrawable().setLevel(1);
               // mPaint.getBackground().setLevel(1);
              //  mEraser.getDrawable().setLevel(0);
             //   mEraser.getBackground().setLevel(0);

                break;
            case R.id.iv_eraser:
                if (mDrawingBoard.getMode() != DrawMode.EraserMode) {
                    mDrawingBoard.setMode(DrawMode.EraserMode);
                }
              //  mPaint.getDrawable().setLevel(0);
             //   mPaint.getBackground().setLevel(0);
             //   mEraser.getDrawable().setLevel(1);
             //   mEraser.getBackground().setLevel(1);
                break;
            case R.id.iv_clean:
                alertDialogClean();
                break;
            case R.id.iv_last:
                mDrawingBoard.lastStep();
                break;
            case R.id.iv_next:
                mDrawingBoard.nextStep();
                break;
        }
    }
    //设置画板清空对话框
    private void alertDialogClean(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要请空画板吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               mDrawingBoard.clean();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final  AlertDialog dialog = builder.show();
        dialog.show();
    }
}
