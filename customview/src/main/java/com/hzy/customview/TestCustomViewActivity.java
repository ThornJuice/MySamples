package com.hzy.customview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.hzy.customview.imageview.ScalableImageView;

public class TestCustomViewActivity extends AppCompatActivity {
    ScalableImageView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test_customer_view);

        initView();

    }

    private void initView() {
        customView = findViewById(R.id.customView);
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TestCustomViewActivity", "点击了");
            }
        });
    }
}