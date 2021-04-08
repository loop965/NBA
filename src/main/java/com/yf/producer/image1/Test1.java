package com.yf.producer.image1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yf
 * @date: 2021/04/06  13:11
 * @desc:
 */
public class Test1 {
    public static void main(String[] args) {
        int count = 100;
        List<String> stringList = new ArrayList<>();
        stringList.forEach(System.out::println);
        long s1 = System.currentTimeMillis();
        long s2 = System.currentTimeMillis();
        new Thread(() ->{
            for (int i = 0; i < count; i++) {
                stringList.add(i+"");
                System.out.println(i);
            }
        }).start();
        System.out.println("done " + (s2 - s1));
    }

}
