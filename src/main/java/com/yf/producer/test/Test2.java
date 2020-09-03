package com.yf.producer.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yf
 * @date: 2020/08/26  16:30
 * @desc:
 */
public class Test2 {
    public static String toString(List<String> strings) throws NoSuchFieldException, IllegalAccessException {
        int len = 0;
        for (String string : strings) {
            len += string.length();
        }
        Field field = String.class.getDeclaredField("value");
        char[] cs = new char[len];
        int start = 0;
        for (String string : strings) {
            field.setAccessible(true);
            char[] chars = (char[]) field.get(string);
            System.arraycopy(chars, 0, cs, start, chars.length);
            start += chars.length;
        }
        return new String(cs);
    }


    public static String toString2(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static String toString3(List<String> strings){
        int len = 0;
        for (String string : strings) {
            len += string.length();
        }
        char[] cs = new char[len];
        int start = 0;
        for (String string : strings) {
            char[] chars =  string.toCharArray();
            System.arraycopy(chars, 0, cs, start, chars.length);
            start += chars.length;
        }
        return new String(cs);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 50000000; i++) {
            list.add("test");
        }

        long t1 = System.currentTimeMillis();


        toString(list);

        long t2 = System.currentTimeMillis();

        toString2(list);

        long t3 = System.currentTimeMillis();

        toString3(list);

        long t4 = System.currentTimeMillis();

        System.out.println(t2-t1);

        System.out.println(t3-t2);

        System.out.println(t4-t3);

    }
}
