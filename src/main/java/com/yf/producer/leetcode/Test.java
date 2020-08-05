package com.yf.producer.leetcode;

/**
 * @author: yf
 * @date: 2020/04/23  14:13
 * @desc:
 */
public class Test {

    public static void main(String[] args) {
        int[] nums2 = {-4,1,-2,3};
        for (int i = 0; i < nums2.length; i++) {
            while (nums2[i] < 0){
                System.out.println(nums2[i]);
                nums2[i] = 1;
            }
        }
    }

}
