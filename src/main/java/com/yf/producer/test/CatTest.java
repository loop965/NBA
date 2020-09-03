package com.yf.producer.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: yf
 * @date: 2020/08/31  16:16
 * @desc:
 */
public class CatTest {
    public static void main(String[] args) {
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "cat.list.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "catListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        JSONArray jsonArray = parseResult.getJSONArray("data");
        JSONObject map = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        jsonArray.forEach(e ->{
            JSONObject jsonObject = (JSONObject) e;
            map.put(jsonObject.getString("catCode"),jsonObject.getString("catName"));
            jsonArray1.add(jsonObject.getString("catName"));
            System.out.println(jsonObject.getString("catName"));
        });
        System.out.println(map);
        getShop();
        getStyle();
        getSeason();
        getColor();
    }

    public static void getStyle(){
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "series.list.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "seriesListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        JSONArray jsonArray = parseResult.getJSONArray("data");
        JSONObject map = new JSONObject();
        jsonArray.forEach(e ->{
            JSONObject jsonObject = (JSONObject) e;
            map.put(jsonObject.getString("seriesCode"),jsonObject.getString("seriesName"));
        });
        System.out.println(map);
    }

    public static void getSeason(){
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "season.list.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "seasonListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        JSONArray jsonArray = parseResult.getJSONArray("data");
        JSONObject map = new JSONObject();
        jsonArray.forEach(e ->{
            JSONObject jsonObject = (JSONObject) e;
            map.put(jsonObject.getString("seasonCode"),jsonObject.getString("seasonName"));
        });
        System.out.println(map);
    }

    public static void getColor(){
        long s1 = System.currentTimeMillis();
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "color.list.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "colorListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        JSONArray jsonArray = parseResult.getJSONArray("data");
        JSONObject map = new JSONObject();
        jsonArray.forEach(e ->{
            JSONObject jsonObject = (JSONObject) e;
            map.put(jsonObject.getString("colorCode"),jsonObject.getString("colorName"));
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
        System.out.println(map);
    }

    public static void getShop(){
        long s1 = System.currentTimeMillis();
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "sd.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "colorListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        JSONArray jsonArray = parseResult.getJSONArray("data");
        JSONObject map = new JSONObject();
        jsonArray.forEach(e ->{
            JSONObject jsonObject = (JSONObject) e;
            map.put(jsonObject.getString("colorCode"),jsonObject.getString("colorName"));
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
        System.out.println(map);
    }
}
