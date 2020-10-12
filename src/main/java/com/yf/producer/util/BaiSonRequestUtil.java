package com.yf.producer.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author: yf
 * @date: 2020/09/11  10:10
 * @desc: 请求百胜Api
 */
public class BaiSonRequestUtil {

    /**
     * 百胜请求自定义参数
     * @param serviceType 接口类型
     * @param params 参数
     * @return string
     */
    public static String sendRequestWithParams(String serviceType, JSONObject params){
        String url = BaiSonConstant.BASE_URL;
        String date = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String key = BaiSonConstant.KEY;
        String secret = BaiSonConstant.SECRET;
        String version = BaiSonConstant.VERSION;
        String data = "key="+key+"&requestTime="+date+"&secret="+secret+"&version="+version+"&serviceType="+serviceType+"&data="+params.toJSONString();
        String sign = DigestUtils.md5DigestAsHex(data.getBytes());
        String data1 = "&format=json&key="+key+"&sign="+sign+"&requestTime="+date+"&version="+version+"&serviceType="+serviceType+"&data="+params.toJSONString();
        HttpRequest httpRequest = HttpRequest.get(url + data1);
        return httpRequest.execute().body();
    }


    /**
     * 向百胜发送分页请求请求
     * @param serviceType 请求方法
     * @param pageNo 页码
     * @param pageSize 大小
     * @return String
     */
    public static String sendRequest(String serviceType, int pageNo, int pageSize){
        String url = BaiSonConstant.BASE_URL;
        String date = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String key = BaiSonConstant.KEY;
        String secret = BaiSonConstant.SECRET;
        String version = BaiSonConstant.VERSION;
        JSONObject object = new JSONObject();
        object.put("startModified", "1990-11-11 08:00:00");
        object.put("endModified", DateUtil.format(DateUtil.nextWeek(),"yyyy-MM-dd HH:mm:ss"));
        object.put("pageNo", pageNo);
        object.put("pageSize", pageSize);
        String data = "key="+key+"&requestTime="+date+"&secret="+secret+"&version="+version+"&serviceType="+serviceType+"&data="+object.toJSONString();
        String sign = DigestUtils.md5DigestAsHex(data.getBytes());
        String data1 = "&format=json&key="+key+"&sign="+sign+"&requestTime="+date+"&version="+version+"&serviceType="+serviceType+"&data="+object.toJSONString();
        HttpRequest httpRequest = HttpRequest.get(url + data1);
        String res = httpRequest.execute().body();
        return res;
    }
}
