package com.yf.producer.test;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: yf
 * @create: 2019/11/26  10:50
 */
public class Test1 {
    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\Administrator\\.config\\clash\\logs\\2020-03-25-111357.log");
        FileInputStream inputStream = new FileInputStream(file);
//        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//        String line;
//        while ((line = br.readLine()) != null){
//            System.out.println(line);
//        }
        FileOutputStream outputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\.config\\clash\\logs\\test.log"));
        byte[] buf = new byte[1024];
        int byteRead;
        while ((byteRead = inputStream.read(buf)) > 0){
            System.out.println("dd");
            outputStream.write(buf,0,byteRead);
        }
        outputStream.close();
//        br.close();
        inputStream.close();


        int i = 30000;
        List<Integer> list = new ArrayList<>();
        long s1 = System.currentTimeMillis();
        for (int t = 0; t < i; t++){
            list.add(t);
        }
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
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
