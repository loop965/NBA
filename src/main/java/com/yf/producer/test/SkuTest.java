package com.yf.producer.test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: yf
 * @date: 2020/09/01  17:43
 * @desc:
 */
public class SkuTest {
    public static void main(String[] args) {
        int cpu = Runtime.getRuntime().availableProcessors();
        int pageNo = 1;
        int pageSize = 10;
        String serviceType = "sku.list.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "skuListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        System.out.println(parseResult);
        System.out.println();
    }
}
