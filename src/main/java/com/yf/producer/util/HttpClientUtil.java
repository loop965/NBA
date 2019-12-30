package com.yf.producer.util;


import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author: yf
 * @date: 2019/12/18  10:17
 * @desc:
 */

public class HttpClientUtil {

    public static void main(String[] args) throws Exception {
        HttpClientUtil httpClientExample = new HttpClientUtil();
        String result = httpClientExample.sendGet("http://fundgz.1234567.com.cn/js/260108.js?rt=1463558676006");
        System.out.println(result);
    }


    public static String sendGet(String url) throws Exception {

        String result="";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);
            // 获取当前客户端对象
            HttpClient httpClient =  HttpClients.createDefault();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }



}