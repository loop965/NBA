package com.yf.producer.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author: yf
 * @date: 2020/09/09  16:59
 * @desc:
 */
public class ReadTxt {
    public static void main(String[] args) throws IOException {
        // data5.json 是标准
        String data1 =  FileUtils.readFileToString(new File("D:\\GitHub\\comyf\\producer\\src\\main\\java\\com\\yf\\producer\\test\\data5.json"));
        String data2 =  FileUtils.readFileToString(new File("D:\\GitHub\\comyf\\producer\\src\\main\\java\\com\\yf\\producer\\test\\data7.json"));
        Map<Integer,Integer> jsonObject1 = (Map<Integer, Integer>) JSONObject.parse(data1);
        Map<Integer,Integer> jsonObject2 = (Map<Integer, Integer>) JSONObject.parse(data2);

        jsonObject1.forEach((k,v) ->{
            Integer s2 = jsonObject2.get(k);
            if (!v.equals(s2)){
                System.out.println(k);
            }
        });

    }
}
