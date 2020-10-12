package com.yf.producer.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author: yf
 * @date: 2020/09/09  15:42
 * @desc:
 */
@Slf4j
public class Tws {

    public static void main(String[] args) {
        String serviceType = "getKc";
        String params = "{\"pt_cangku_type\":3,\"kc_data\":[{\"spdm\":\"AGC3108\"},{\"spdm\":\"AGC3655\"},{\"spdm\":\"AGC3656\"},{\"spdm\":\"AGC3664\"},{\"spdm\":\"AGC3678\"},{\"spdm\":\"AGC5011\"},{\"spdm\":\"AGD5005\"},{\"spdm\":\"AGD5010\"},{\"spdm\":\"AGD5035\"},{\"spdm\":\"AGD5036\"}]}";
        String result = sendRequest(serviceType,params);
        JSONObject resultJsonObject = JSONObject.parseObject(result);
        String status = resultJsonObject.getString("status");
        if (!"SUCCESS".equals(status)){
            return;
        }
        JSONArray data = resultJsonObject.getJSONArray("data");
        log.info("查询data length：{}",data.size());
        System.out.println(resultJsonObject);
    }

    public static String sendRequest(String serviceType, String params){
        String url = "https://mc3.sqzw.com/release/mc3/webopm/web/?app_act=api/ec&app_mode=func&customer_id=2804";
        String date = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String key = "modoo";
        String secret = "W2w2niQCyRu8YB";
        String version = "0.1";
        String data = "key="+key+"&requestTime="+date+"&secret="+secret+"&version="+version+"&serviceType="+serviceType+"&data="+params;
        String sign = DigestUtils.md5DigestAsHex(data.getBytes());
        String data1 = "&format=json&key="+key+"&sign="+sign+"&requestTime="+date+"&version="+version+"&serviceType="+serviceType+"&data="+params;
        HttpRequest httpRequest = HttpRequest.get(url + data1);
        return httpRequest.execute().body();
    }
}
