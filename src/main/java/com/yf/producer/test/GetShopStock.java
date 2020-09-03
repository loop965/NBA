package com.yf.producer.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author: yf
 * @date: 2020/09/03  09:18
 * @desc:
 */
public class GetShopStock {
    public static void main(String[] args) {
        String serviceType = "getKc";
        int pageNo = 1;
        int pageSize = 10;
        JSONObject params = new JSONObject();
        params.put("pt_cangku_type",0);
        JSONObject kc_data = new JSONObject();
        kc_data.put("sku","AIZ800000101");
//        kc_data.put("goodsSn","AIZ8000");
        kc_data.put("ckdm","001");
        params.put("kc_data",kc_data);
        String result = sendRequest(serviceType,params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);


    }

    public static String sendRequest(String serviceType, JSONObject params){
        String url = "https://mc3.sqzw.com/release/mc3/webopm/web/?app_act=api/ec&app_mode=func&customer_id=2804";
        String date = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String key = "modoo";
        String secret = "W2w2niQCyRu8YB";
        String version = "0.1";
        String data = "key="+key+"&requestTime="+date+"&secret="+secret+"&version="+version+"&serviceType="+serviceType+"&data="+params.toJSONString();
        String sign = DigestUtils.md5DigestAsHex(data.getBytes());
        String data1 = "&format=json&key="+key+"&sign="+sign+"&requestTime="+date+"&version="+version+"&serviceType="+serviceType+"&data="+params.toJSONString();
        HttpRequest httpRequest = HttpRequest.get(url + data1);
        return httpRequest.execute().body();
    }
}
