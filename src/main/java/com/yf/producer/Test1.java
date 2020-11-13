package com.yf.producer;

/**
 * @author: yf
 * @date: 2020/10/26  14:51
 * @desc:
 */
public class Test1 {
    public static void main(String[] args) {
        String mobile = "211111111111";
        if (!(mobile.length() == 11 && mobile.startsWith("1"))){
            System.out.println("手机号码错误");
        }
        System.out.println("fd");
    }
}
