package com.hzy.wan.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.hzy.wan.App;

public class Test {
    Class<?> classBook;

    {
        try {
            classBook = Class.forName("com.android.peter.reflectdemo.Book");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Object objectBook;

    {
        try {
            objectBook = classBook.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    void  fun1(ImageView imageView){
       Glide.with(App.getAppContext()).load("").into(imageView);

    }
}
