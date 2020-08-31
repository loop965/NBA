package com.yf.producer.leetcode;

import java.util.*;

/**
 * @author: yf
 * @date: 2020/04/22  15:27
 * @desc: 最接近的三数之和
 */
public class TheLastThree {

    public static void main(String[] args) {

        int[] nums = {-71,-82,-14,-64,-23,-33,-77,93,27,36,-19,94,-65,65,-58,-68,-2,29,60,20,59,-49,91,31,98,-28,-12,
                -14,-49,55,-70,0,-3,-21,54,12,94,-81,-35,33,11,15,15,34,-40,36,-43,-48,-98,76,42,30,-14,85,56,-82,11,
                -23,92,56,-11,41,60,67,23,-75,96,0,-14,-25,-70,-70,70,-74,-16,-94,-20,-48,56,-70,-55,-10,47,92,-63,98,
                91,-45,26,88,47,35,-48,79,-96,-48,24,-32,-36,-100,-7,-63,-64,23,-59,98,92,-38,-48,-8,31,60,82,93,-15,
                82,-89,86,50,-74,20,-46,35,50,-67,-26,81,18,-43,-45,-69,-13,2,41,-75,94,-17,-38,-90,35,79,-19,79,54,
                -82,-47,-58,-74,-25,-45,-96,-26,45,-31,85,-57,26,89,-46,94,6,-78,-66,-91};
        int target = -250;
        int[] nums2 = {-4,1,-2,8};
//        int total = threeSumClosest(nums,target);
        int total1 = threeSumClosest1(nums,target);
        int total2 = threeSumClosest2(nums,target);
        System.out.println(total1);
        System.out.println(total2);
    }


    private static int threeSumClosest(int[] nums, int target) {
        Map<Integer, String> map = new TreeMap<>();
        for (int num : nums) {
            int dif = Math.abs(target - num);
            if (map.containsKey(dif)) {
                String value = map.get(dif);
                map.put(dif, value + "," + num);
            } else {
                map.put(dif, num + "");
            }
        }
        Object[] keySet =  map.keySet().toArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keySet.length; i++) {
            if (i > 2){
                break;
            }
            int key = (Integer) keySet[i];
            String value = map.get(key);
            sb.append(value).append(",");
        }
        String[] s =  sb.toString().split(",");
        int total = 0;
        for (int i = 0; i < s.length; i++) {
            if (i > 2){
                break;
            }
            total = total + Integer.parseInt(s[i]);
        }
        return total;
    }
    // {0,2,1,-3}
    private static int threeSumClosest1(int[] nums, int target) {
        long s1 = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length -2; i++) {
            for (int i1 = i+1; i1 < nums.length - 1; i1++) {
                for (int i2 = i1+1; i2 < nums.length; i2++) {
                    int sum = nums[i] + nums[i1] + nums[i2];
                    list.add(sum);
                }
            }
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            int dif = Math.abs(target - list.get(i));
            if (map.containsKey(dif)){
                Integer value = map.get(dif);
                map.put(dif,list.get(i));
            }else {
                map.put(dif,list.get(i));
            }
        }
        Object[] keySet = map.keySet().toArray();
        long s2 = System.currentTimeMillis();
        System.out.println("method1 take time:" + (s2 - s1));
        return map.get(keySet[0]);
    }

    private static int threeSumClosest2(int[] nums, int target) {
        long s1 = System.currentTimeMillis();
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if (Math.abs(target - sum) < Math.abs(target - ans)){
                    ans = sum;
                }
                if (sum < target){
                    start++;
                }else {
                    end--;
                }
            }
        }
        long s2 = System.currentTimeMillis();
        System.out.println("method2 take time:" + (s2 - s1));
        return ans;
    }

}
