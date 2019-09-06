package com.ju.baselibrary.http;

public interface HttpStringCallBack {
    /**
     * 请求成功的回调
     * @param result 返回结果
     */
    public void onSuccess(Object result);

    /**
     * 请求失败的回调
     * @param code  错误标识码
     * @param msg   错误描述
     */
    public void onFailure(int code, String msg);

}
