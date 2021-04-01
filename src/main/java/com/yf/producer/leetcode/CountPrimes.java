package com.yf.producer.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yf
 * @date: 2020/12/03  11:13
 * @desc:
 */
public class CountPrimes {


    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        int a = countPrimes(499979);
        long s2 = System.currentTimeMillis();
        System.out.println(a);
        System.out.println((s2-s1)+" ms");
    }

    public static int countPrimes(int n) {
        if(n <= 1){
            return 0;
        }
        int count = 0;
        for (int i = 2; i < n ; i++) {
            int sum = 0;
            for (int j = 2; j < i; j++) {
                int a = i % j;
                if (a == 0){
                    sum++;
                    break;
                }
            }
            if (sum == 0){
                count++;
            }
        }
        return count;
    }

}
