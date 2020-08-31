package com.yf.producer.test;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yf
 * @date: 2020/03/19  15:17
 * @desc:
 */
public class StreamDemo {

    public static void main(String[] args) {
        String s = "a good   example";
        String[] strings = s.trim().replaceAll("\\s+"," ").split(" ");
        int j = strings.length - 1;
        for (int i = 0; i < strings.length / 2 ; i++) {
           String pre = strings[i];
           String last = strings[j];
           strings[i] = last;
           strings[j] = pre;
           j--;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i == strings.length -1){
                result.append(strings[i]);
            }else {
                result.append(strings[i]).append(" ");
            }
        }


        System.out.println(result.toString());


//        List<String> list = Arrays.asList("stack","overe");
//        List<String[]> s = list.stream().map(word -> word.split("")).distinct().collect(Collectors.toList());
//        List<String> s1 = list.stream().flatMap(word -> Arrays.stream(word.split(""))).distinct().collect(Collectors.toList());
//        System.out.println(s);
//        System.out.println(s1);
    }

}
