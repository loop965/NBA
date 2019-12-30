package com.yf.producer.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yf
 * @create: 2019/11/26  10:50
 */
public class Test1 {
    public static void main(String[] args) {

        String beginTime = "2018-07-30 12:26:32";
        String endTime = "2018-07-29 12:26:32";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);

            int compareTo = date1.compareTo(date2);

            System.out.println(compareTo);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
