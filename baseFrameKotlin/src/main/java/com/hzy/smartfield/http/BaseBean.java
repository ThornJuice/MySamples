package com.hzy.smartfield.http;

import java.util.List;

public class BaseBean<T> {


    public String errorCode;
    public String errorMsg;
    // 下面两个数据 根据实际使用 用哪个取哪个 判空取值
   // public T data;//有些接口返回的是 data  jsonobject
    public List<T> data;//有些接口 返回的是 list   jsonarray
}
