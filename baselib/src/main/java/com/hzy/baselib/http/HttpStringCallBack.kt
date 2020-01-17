package com.hzy.baselib.http

interface HttpStringCallBack {
    /**
     * 请求成功的回调
     * @param result 返回结果
     */
    fun onSuccess(result: String)

    /**
     * 请求失败的回调
     * @param code  错误标识码
     * @param msg   错误描述
     */
    fun onFailure(code: Int, msg: String? = "errorMsg")

}
