package com.yf.producer.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yf
 * @date: 2020/09/01  17:43
 * @desc:
 */
public class SkuTest {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date1 = sdf.parse("2020-09-15 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date1);
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
