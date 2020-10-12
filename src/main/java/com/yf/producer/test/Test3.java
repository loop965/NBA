package com.yf.producer.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yf.producer.pojo.BrdProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * @author: yf
 * @date: 2020/08/26  17:10
 * @desc:
 */

@Slf4j
public class Test3 {

    public static final Map<String,Integer> STOCK_MAP = new HashMap<>();

    private static final List<BrdProduct> productList = new ArrayList<>();

    private static Set<String> stateSet = new HashSet<>();

    private static final Set<String> ckSet = new HashSet<>();


    public static void main(String[] args){
        int pageNo = 1;
        int pageSize = 1000;
        String serviceType = "stock.list.get";
        String result = sendRequest(serviceType,pageNo,pageSize);
        String resultKey = "stockListGet";
        JSONObject parseResult = parseResult(result,resultKey);
        int pageTotal = parseResult.getInteger("pageTotal");
        JSONArray jsonArray = parseResult.getJSONArray("data");
        parseGoodStockList(jsonArray);
        for (int i = 2; i <= pageTotal; i++){
            result = sendRequest(serviceType,i,pageSize);
            JSONObject parseResult1 = parseResult(result,resultKey);
            parseGoodStockList(parseResult1.getJSONArray("data"));
        }

        String goodServiceType1 = "goods.list.get";
        String goodResult1 = sendRequest(goodServiceType1,pageNo,pageSize);
        String goodResultKey1 = "goodsListGet";
        JSONObject goodParseResult1 = parseResult(goodResult1,goodResultKey1);
        int pageTotal1 = goodParseResult1.getInteger("pageTotal");
        JSONArray jsonArray1 = goodParseResult1.getJSONArray("data");
        parseGoodList(jsonArray1);
        for (int i = 2; i <= pageTotal1; i++){
            goodResult1 = sendRequest(goodServiceType1,i,pageSize);
            JSONObject parseResult11 = parseResult(goodResult1,goodResultKey1);
            parseGoodList(parseResult11.getJSONArray("data"));
        }
    }


    public static JSONObject parseResult(String result,String resultKey){
        JSONObject response = new JSONObject();
        JSONObject jsonObject = JSONObject.parseObject(result);
        String status = "api-success";
        int pageTotal = 0;
        if (status.equals(jsonObject.getString("status"))){
            JSONObject data2 = jsonObject.getJSONObject("data");
            JSONObject page = data2.getJSONObject("page");
            int totalResult = page.getInteger("totalResult");
            pageTotal = page.getInteger("pageTotal");
            JSONArray goodsListGet = data2.getJSONArray(resultKey);
            response.put("data",goodsListGet);
        }else {
            log.warn("请求失败:{}",jsonObject);
        }
        response.put("pageTotal",pageTotal);

        return response;
    }




    public static void parseGoodList(JSONArray goodList){
        goodList.forEach(good -> {
            BrdProduct brdProduct = new BrdProduct();
            JSONObject product = (JSONObject) good;
            String style = product.getString("seriesName");
            String styleCode = product.getString("seriesCode");
            String seasonCode = product.getString("seasonCode");
            String id = product.getString("goods_id");
            String productNum = product.getString("goodsSn");
            String catCode = product.getString("catCode");
            String topCatCode = product.getString("topCatCode");
            Date createTime = product.getDate("created");
            Date updateTime = product.getDate("modified");
            String state = product.getString("is_delete");
            stateSet.add(state);
            brdProduct.setUid(id);
            brdProduct.setProductNum(productNum);
            brdProduct.setStyles(styleCode);
            brdProduct.setCreateTime(createTime);
            brdProduct.setUpdateTime(updateTime);
            brdProduct.setState("1");
            Integer stock = STOCK_MAP.get(id) == null ? 0 : STOCK_MAP.get(id);
            brdProduct.setInStock(stock);
            productList.add(brdProduct);
        });
    }

    public static void parseGoodStockList(JSONArray goodStockList){
        goodStockList.forEach(e -> {
            JSONObject item = (JSONObject) e;
            String productId = item.getString("goods_id");
            String sku = item.getString("sku");
            Integer sl = item.getInteger("sl");
            log.info("{}",item);
            if ("AIZ561000902".equals(sku)){
                log.info("{}",item);
            }
            synchronized (ckSet){
                ckSet.add(item.getString("ckmc"));
            }
            if (sl > 0){
                STOCK_MAP.put(productId,sl);
            }
        });

    }





    public static String sendRequest(String serviceType, int pageNo, int pageSize){
        // goods.list.get G91C002  season.list.get
        String url = "https://mc3.sqzw.com/release/mc3/webopm/web/?app_act=api/ec&app_mode=func&customer_id=2804";
        String date = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String key = "modoo";
        String secret = "W2w2niQCyRu8YB";
        String version = "0.1";
        JSONObject object = new JSONObject();
        object.put("startModified", "1990-11-11 08:00:00");
        object.put("endModified", "2020-12-11 08:00:00");
        object.put("pageNo", pageNo);
        object.put("pageSize", pageSize);
        String data = "key="+key+"&requestTime="+date+"&secret="+secret+"&version="+version+"&serviceType="+serviceType+"&data="+object.toJSONString();
        String sign = DigestUtils.md5DigestAsHex(data.getBytes());
        String data1 = "&format=json&key="+key+"&sign="+sign+"&requestTime="+date+"&version="+version+"&serviceType="+serviceType+"&data="+object.toJSONString();
        HttpRequest httpRequest = HttpRequest.get(url + data1);
        return httpRequest.execute().body();
    }


}
