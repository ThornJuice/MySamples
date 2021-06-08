package com.ju.designpatterns.responsibility2;

/**
 * @author: wxj
 * @date: 2021/4/9
 * @description:
 */
public class MyInterceptorTwo implements MyInterceptor {
    @Override
    public String handle(Chain chain) {
        String request = chain.getRequest();
        String result = request + "拦截器2把身体装上了；";
        return chain.proceed(result);
    }
}
