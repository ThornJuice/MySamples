package com.jiushang.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxj
 * @date: 2021/5/8
 * @description:
 */
public class BinarySearch {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            numbers.add(i);
        }
        binarySearch(numbers, 888);
    }

    //查二分法查找
    public static int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        int step = 0;
        int mid = 0;
        while (low <= high) {
            step++;
            mid = (low + high) / 2;//中间下标
            System.out.println("mid = " + mid);
            int guess = list.get(mid);//取中间值
            if (guess == target) {
                System.out.println("猜对了guess = " + guess);
                System.out.println("一共用了" + step + "步");
                return mid;
            } else if (guess > target) {
                high = mid - 1;
                System.out.println("猜大了high = " + high);
            } else if (guess < target) {
                low = mid + 1;
                System.out.println("猜小了low = " + mid);
            }
        }
        System.out.println("一共用了" + step + "步,未查找到");
        return -1;
    }
}
