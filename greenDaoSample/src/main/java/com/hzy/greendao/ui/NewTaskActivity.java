package com.hzy.greendao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.hzy.greendao.R;
import com.hzy.greendao.Task;
import com.hzy.greendao.db.DbHelperUtil;
import com.hzy.greendao.db.TaskHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NewTaskActivity extends AppCompatActivity {
    TaskHelper taskHelper;
    EditText edtTaskName;
    EditText edtTaskContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        initView();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.tempSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tempSave();
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("1");
                } catch (Exception e) {

                }
            }
        });
    }

    private void tempSave() {
        String name = edtTaskName.getText().toString().trim();
        String content = edtTaskContent.getText().toString().trim();
        taskHelper = DbHelperUtil.getTaskHelper();
        Task task = new Task();
        task.setTaskName(name);
        task.setTaskContent(content);
        taskHelper.save(task);
    }

    private void initView() {
        edtTaskName = findViewById(R.id.edt_taskName);
        edtTaskContent = findViewById(R.id.edt_taskContent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String  o) {

    }
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
