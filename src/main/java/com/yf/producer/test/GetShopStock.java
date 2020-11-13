package com.yf.producer.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yf.producer.util.BaiSonRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.*;

/**
 * @author: yf
 * @date: 2020/09/03  09:18
 * @desc:
 */
@Slf4j
public class GetShopStock {

    private static  Map<Integer,Integer> COUNT_MAP ;
    public static void getKu(){
        long s2 = currentTimeMillis();
        String serviceType = "getKc";
        JSONObject params = new JSONObject();
        params.put("pt_cangku_type",3);
        JSONArray jsonArray = new JSONArray();
        JSONObject kc_data = new JSONObject();
        kc_data.put("spdm","S03C045");
        jsonArray.add(kc_data);
        params.put("kc_data",jsonArray);
        String result = sendRequest(serviceType,params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        out.println(jsonObject);
        JSONArray j = jsonObject.getJSONArray("data");
        AtomicInteger count = new AtomicInteger();
        j.forEach(e ->{
            JSONObject object = (JSONObject) e;
            String sku = object.getString("sku");
            if (StringUtils.equals("920-8400202",sku)){
                count.set(count.get() + object.getInteger("sl"));
            }
        });
        long s21 = currentTimeMillis();
        out.println(count.get());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        getKu();

//        for (int j = 0; j < 5 ;j++) {
//            COUNT_MAP = new ConcurrentHashMap<>();
//            String filePath = "D:\\GitHub\\comyf\\producer\\src\\main\\java\\com\\yf\\producer\\test\\spdm.json";
//            String data1 =  FileUtils.readFileToString(new File(filePath));
//            List<String> list = (List<String>) JSONObject.parse(data1);
//            List<String> arrayList = new ArrayList<>(list);
//            Collections.sort(arrayList);
//            long s3 = System.currentTimeMillis();
//            String serviceType = "getKc";
//
//            int pageSize = 100;
//            int listSize = arrayList.size();
//            int count = listSize / pageSize ;
//            if (listSize % pageSize != 0) {
//                count++;
//            }
//            ExecutorService threadPoolTaskExecutor = Executors.newFixedThreadPool(13);
//            CountDownLatch countDownLatch = new CountDownLatch(count);
//            for (int i = 0; i < count; i++) {
//                int fromIndex = i * pageSize;
//                int toIndex = (i + 1) * pageSize;
//                if (toIndex > listSize) {
//                    toIndex = listSize;
//                }
//                List<String> subList = arrayList.subList(fromIndex, toIndex);
//                int finalI = i;
//                threadPoolTaskExecutor.execute(() -> {
//                    sendRequestGetShopStock(serviceType,subList, finalI);
//                    countDownLatch.countDown();
//                });
//            }
//            countDownLatch.await(7, TimeUnit.MINUTES);
//            log.info("COUNT_MAP:{},",JSONObject.toJSONString(COUNT_MAP));
//            FileUtil.appendString(JSONObject.toJSONString(COUNT_MAP),"D:\\GitHub\\comyf\\producer\\src\\main\\java\\com\\yf\\producer\\test\\data8.json","utf-8");
//            FileUtil.appendString("\n","D:\\GitHub\\comyf\\producer\\src\\main\\java\\com\\yf\\producer\\test\\data8.json","utf-8");
//            long s5 = System.currentTimeMillis();
//            log.info("aline拉取店铺库存total：{}s",(s5 - s3)/1000);
//            long s4 = System.currentTimeMillis();
//            log.info("同步aline店铺库存：{}s",(s4 - s3)/1000);
//        }
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










    private static void sendRequestGetShopStock(String serviceType, List<String> subList, int finalI){
        JSONObject params = new JSONObject();
        params.put("pt_cangku_type",3);
        JSONArray jsonArray = new JSONArray();
        subList.forEach(e -> {
            JSONObject kcData = new JSONObject();
            kcData.put("spdm",e);
            jsonArray.add(kcData);
        });
        params.put("kc_data",jsonArray);
        long s1 = currentTimeMillis();
        String result = BaiSonRequestUtil.sendRequestWithParams(serviceType,params);
        long s2 = currentTimeMillis();
        log.info("第{}次查询店铺库存：{}ms", finalI,(s2 - s1));
        if (StringUtils.isBlank(result)){
            log.warn("第{}次查询店铺库存结果为空：{}", finalI,result);
            return;
        }
        parseShopKcResult(result, finalI);
    }

    /**
     * 解析门店库存查询结果
     * @param result 查询结果
     * @param i i
     */
    private static void parseShopKcResult(String result, int i) {
        JSONObject resultJsonObject = JSONObject.parseObject(result);
        String status = resultJsonObject.getString("status");
        if (!"SUCCESS".equals(status)) {
            log.warn("第{}次查询店铺库存结果为：{}", i,resultJsonObject);
            return;
        }
        JSONArray data = resultJsonObject.getJSONArray("data");

        if (data == null) {
            COUNT_MAP.put(i, -1);
            log.warn("第{}次查询data is null", i);
            return;
        }
        try {
            COUNT_MAP.put(i, data.size());
            log.info("第{}次查询data length：{}", i, data.size());
        } catch (Exception e) {
            log.warn("第{}次查询data:{},{}", i, data, e);
        }
        data.parallelStream().forEach(e -> {
            JSONObject item = (JSONObject) e;
            String spdm = item.getString("spdm");
            String ckdm = item.getString("ckdm");
            String sku = item.getString("sku");
            Integer sl = item.getInteger("sl");
            String colorCode = sku.substring(0,sku.length()-2).split(spdm)[1];
            String newProductNum = spdm + "-" + colorCode;
        });
    }


    // 南京商厦仓
    public static String getTheKu(int pageNo,int pageSize){
        String serviceType = "gethzKc";
        JSONObject params = new JSONObject();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
//        params.put("hzckdm","000,001,002,003,004,005,006,007,008,009,010,A001,A002,A003,A004,A005,A006,A007,A008,A009,A010,A011,A012,A013,A014,A015,A016,D001,D002,D003,D004,D005,D006,H001,S001,S002,S003,S004,S005,S006,S007,S008,D007,D008,A017,010_1,004_1,002_1,QD001,NS01,LZ000_000,D009,017,004_2,004_3,004_4,D010,PF001,PFCK,D011,A018");
//        params.put("hzckdm","S003");
//        params.put("goods_sn","PM19005");
//        params.put("sku","PM1900500302");
        String result = sendRequest(serviceType,params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        out.println(jsonObject);
        return result;
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
