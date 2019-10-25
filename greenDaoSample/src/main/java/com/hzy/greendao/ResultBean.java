package com.hzy.greendao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class ResultBean {
    @Id(autoincrement = true)
    Long id;
    @Convert(converter = TaskConvert.class, columnType = String.class)
    Task datas;
    

    public ResultBean() {
    }

    @Generated(hash = 563025205)
    public ResultBean(Long id, Task datas) {
        this.id = id;
        this.datas = datas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getDatas() {
        return datas;
    }

    public void setDatas(Task datas) {
        this.datas = datas;
    }

    public static  class TaskConvert implements PropertyConverter<Task, String> {
        @Override
        public Task convertToEntityProperty(String databaseValue) {
            return JSONObject.parseObject(databaseValue, Task.class);
        }

        @Override
        public String convertToDatabaseValue(Task entityProperty) {
            return JSON.toJSONString(entityProperty);
        }
    }
}