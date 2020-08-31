package com.yf.producer.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: yf
 * @create: 2019/11/26  10:50
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        List<String> stringList = new ArrayList<>();

        int hash = HashUtil.apHash("test");
        int hash1 = HashUtil.bkdrHash("test");
        int hash2 = HashUtil.bkdrHash("test");
        System.out.println(hash);
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/yangfei/Documents/HBuilderProjects/temp/data.json"));
        List<String> lsit = IOUtils.readLines(fileInputStream);
        String content = lsit.get(0);
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        jsonArray.forEach(e ->{
            JSONObject object = (JSONObject) e;
            stringList.add(object.getString("productnum"));
        });
        System.out.println();


    }
}
