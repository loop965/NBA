package com.yf.producer.test;

import cn.hutool.http.HttpRequest;

/**
 * @author: yf
 * @date: 2020/10/10  16:09
 * @desc:
 */
public class DownHtml {
    public static void main(String[] args) {
        HttpRequest httpRequest = HttpRequest.get("https://www.xiaohongshu.com/discovery/item/5f755a4c0000000001009d6f");
        System.out.println(httpRequest.execute().body());

    }
}
