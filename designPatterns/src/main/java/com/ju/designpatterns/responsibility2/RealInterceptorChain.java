package com.ju.designpatterns.responsibility2;

import java.util.List;

/**
 * @author: wxj
 * @date: 2021/4/9
 * @description:
 */
public class RealInterceptorChain implements MyInterceptor.Chain {

    private List<MyInterceptor> interceptors;
    private int index = 0;
    private String response;

    public RealInterceptorChain(List<MyInterceptor> list) {
        this.interceptors = list;
    }

    @Override
    public String proceed(String request) {
        response = request;
        System.out.println("index的值：" + index);
        if (interceptors == null || interceptors.size() == 0) return response;
        if (index > interceptors.size() - 1) {
            System.out.println("拦截器结束");
        } else {
            MyInterceptor interceptor = interceptors.get(index);
            index++;
            System.out.println("处理后的response：" + response);
            response = interceptor.handle(this);
        }
        return response;

    }

    @Override
    public String getRequest() {
        return response;
    }
}
