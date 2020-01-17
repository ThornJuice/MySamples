package com.hzy.baselib.http;

import java.util.List;

public class BaseResp<T> {
    public String errorCode;
    public String errorMsg;
    public List<T> data;
}
