package com.jiushang.arithmetic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxj
 * @date: 2021/5/8
 * @description:
 */
public class ChooseSearch {
    public static void main(String[] args) {
        int[] numbers = {5,3,6,2,10};

        ArrayList list = sort(numbers);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

    public static   ArrayList sort(int[] numbers ) {
        ArrayList<Integer> newList = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            int small = findSmallest(numbers);
            System.out.println("最小值是："+small);
            newList.add(numbers[i]);
        }

        return newList;
    }
    //最小元素下标
    public static int findSmallest(int[] list) {
        int min = list[0];
        int min_index = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] < min) {
                min = list[i];
                min_index = i;
            }
        }
        return min_index;
    }
}
