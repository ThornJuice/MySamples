package com.hzy.smartfield.http;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络请求json 转bean 回调
 *
 * @param <T>
 */
public abstract class HttpObjectCallBack<T> {
    private Class<T> aClass;
    private boolean isNeedDBJoin = false;

    public HttpObjectCallBack(Class<T> aClass) {
        this.aClass = aClass;
    }

    public HttpObjectCallBack(Class<T> aClass, boolean isNeedDBJoin) {
        this.aClass = aClass;
        this.isNeedDBJoin = isNeedDBJoin;
    }

    public boolean isNeedDBJoin() {
        return isNeedDBJoin;
    }

    public Class<T> getaClass() {
        return aClass;
    }

    public void JsonToBean(String json) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                String code = jsonObject.optString("errorCode", "");
                String msg = jsonObject.optString("errorMsg", "");
               // String data = jsonObject.optString("data");
                String list = jsonObject.optString("data");

                BaseBean<T> baseBean = new BaseBean<T>();
                baseBean.errorCode = code;
                baseBean.errorMsg = msg;
               // baseBean.data = getOne(data, aClass);
                baseBean.data = getArr(list, aClass);

                onSuccess(baseBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void onSuccess(BaseBean<T> result);

    public abstract void onFailure(int code, String msg);

    private T getOne(String json, Class clazz) {
        if (!TextUtils.isEmpty(json)) {
            Type type = new ParameterizedTypeImpl(clazz);
            Type type1 = ((ParameterizedTypeImpl) type).getActualTypeArguments()[0];
            T bean = JSON.parseObject(json, type1);
            return bean;
        } else {
            return null;
        }
    }

    private List<T> getArr(String jsonArr, Class a) {
        if (!TextUtils.isEmpty(jsonArr)) {
            List<T> beans = new ArrayList<>();
            if (jsonArr.startsWith("{") && jsonArr.endsWith("}")) {
                T va = getOne(jsonArr, a);
                beans.add(va);
            } else if (jsonArr.startsWith("[") && jsonArr.endsWith("]")) {
                try {
                    beans = JSON.parseArray(jsonArr, a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return beans;
        } else {
            return null;
        }
    }

    private class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}

