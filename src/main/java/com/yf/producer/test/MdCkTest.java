package com.yf.producer.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: yf
 * @date: 2020/09/07  11:03
 * @desc: 门店仓库列表
 */
public class MdCkTest {

    public static void main(String[] args) {
        String key = "md_cangku_get";
        int pageNo = 1;
        int pageSize = 100;
        String serviceType = "cangku_get";
        String result = Test3.sendRequest(serviceType,pageNo,pageSize);
        JSONObject parseResult = JSONObject.parseObject(result);
        System.out.println(parseResult);
        JSONArray jsonArray = parseResult.getJSONArray("data");
        JSONArray jsonArray1 = (JSONArray)jsonArray.get(0);
        StringBuilder sb = new StringBuilder();
        jsonArray1.forEach(e ->{
            JSONObject item = (JSONObject) e;
            String kcmc = item.getString("ckmc");
            if (kcmc.equals("南京商厦仓")){
                System.out.println(item);
            }
            sb.append(((JSONObject) e).getString("ckdm")).append(",");

        });
        System.out.println(sb.toString());
    }
}
