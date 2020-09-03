package com.yf.producer.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yf
 * @date: 2020/09/03  11:02
 * @desc:
 */

@Slf4j
public class GetOrderList {


    public static void main(String[] args) {
        long s1 = System.currentTimeMillis();
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "order.split.list.get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "orderSplitListGet";
        JSONObject parseResult = Test3.parseResult(result,resultKey);
        JSONArray jsonArray = parseResult.getJSONArray("data");

        jsonArray.forEach(e ->{
            JSONObject jsonObject = (JSONObject) e;
            log.info("{}",jsonObject);
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);

    }
}
