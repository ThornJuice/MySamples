package com.ju.designpatterns.responsibility2;

/**
 * @author: wxj
 * @date: 2021/4/9
 * @description:
 */
public interface MyInterceptor {
    String handle(Chain chain);

    interface Chain {
        String proceed(String requestStr);

        String getRequest();
    }
}
