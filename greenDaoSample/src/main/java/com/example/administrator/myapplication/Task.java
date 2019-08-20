package com.example.administrator.myapplication;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Task {
    @Id(autoincrement = true)
    Long id;
    String taskName;//任务名称
    String taskContent; //任务描述
    @Generated(hash = 1408159634)
    public Task(Long id, String taskName, String taskContent) {
        this.id = id;
        this.taskName = taskName;
        this.taskContent = taskContent;
    }
    @Generated(hash = 733837707)
    public Task() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskContent() {
        return this.taskContent;
    }
    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}
