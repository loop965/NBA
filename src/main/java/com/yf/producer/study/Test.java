package com.yf.producer.study;

/**
 * @author: yf
 * @date: 2020/08/20  13:28
 * @desc:
 */
public class Test {


    public static void main(String[] args) {
        MyConcurrentHashMap<String,String> map = new MyConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++){
            map.put("i",i);
            System.out.println("dd");
            int a = 2;
            int c = 3;
        }
        System.out.println("dev");
        System.out.println(map.size());
    }

}
