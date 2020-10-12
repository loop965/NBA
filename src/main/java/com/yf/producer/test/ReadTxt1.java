package com.yf.producer.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yf
 * @date: 2020/09/09  16:59
 * @desc:
 */
public class ReadTxt1 {

    public static final Map<String,Integer> MODOO_COLOR_MAP = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // data5.json 是标准

        List relation =  FileUtils.readLines(new File("D:\\color.txt"));
        List data2 =  FileUtils.readLines(new File("D:\\modoo-color.txt"));

        String baiSonColor =  FileUtils.readFileToString(new File("D:\\GitHub\\comyf\\producer\\src\\main\\java\\com\\yf\\producer\\test\\baison-color.json"));

        Map<String,String> jsonObject1 = (Map<String, String>) JSONObject.parse(baiSonColor);

        data2.forEach(e ->{
            String[] content =  e.toString().split("\t");
            MODOO_COLOR_MAP.put(content[1],Integer.parseInt(content[0]));
        });

        relation.forEach(e ->{
            String[] content =  e.toString().split("\t");
            String baiSon = content[0];
            String modoo = content[2];
            Integer modooKey = MODOO_COLOR_MAP.get(modoo);
            if (modooKey == null){
                System.out.println();
            }
            System.out.println("COLOR_MAP.put(\"" + jsonObject1.get(baiSon) + "\"," + modooKey +");");
        });





    }
}
