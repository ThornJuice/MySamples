package com.ju.designpatterns.test;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ju.designpatterns.responsibility.Student1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static void main(String[] strings){
        Map<String, Integer> map = new HashMap<String, Integer>() {};
        ArrayList<Student1> list = new ArrayList<Student1>(){};
        Type type = list.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
        for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
            System.out.println(typeArgument.getTypeName());
        }

    }
}
