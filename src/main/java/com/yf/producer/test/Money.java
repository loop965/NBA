package com.yf.producer.test;

import java.util.Scanner;

/**
 * @author: yf
 * @date: 2019/12/11  15:56
 * @desc:
 */
public class Money {

    public static void main(String[] args) {
        while (true){
            String exitCode = "e";
            Scanner existScanner = new Scanner(System.in);
            String code = existScanner.next();
            if ("q".equals(code)){
                exitCode = code;
                break;
            }else {
                System.out.println("error"+code);
            }
        }
    }
}
