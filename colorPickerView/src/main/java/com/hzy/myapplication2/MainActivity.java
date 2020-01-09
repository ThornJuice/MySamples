package com.hzy.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    ProgressView progressView;
    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        FormXCJCBean titleBean2 = new FormXCJCBean();
        titleBean2.total = 10;
        titleBean2.yzg = 5;
        titleBean2.dfy = 2;
        titleBean2.dzg = 0;
        titleBean2.fzcgb = 3;
        progressView =  new ProgressView(this,titleBean2);
        container.addView(progressView);
    }
}
