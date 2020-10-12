package com.yf.producer.task;

import com.yf.producer.aspect.Time;
import com.yf.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author: yf
 * @date: 2020/01/07  10:52
 * @desc:
 */
@Slf4j
@Component
public class StockTask {


//    @Scheduled(cron = "0/10 * 9-14 * * 1-5")
    public static void getStock(){
        String[] codes = new String[]{"sh601633","sz002241","sh601688","sz000651","sh600519","sh603288","sz002456"};
        List<String> list = Arrays.asList(codes);
        log.info("===================================================================================================");
        log.info("股票名称   涨幅    最大涨幅   当前价格  昨日开盘价格 更新时间 ");
        list.forEach(code ->{
            String url = "http://hq.sinajs.cn/list=" + code;
            String result = null;
            try {
                result = HttpClientUtil.sendGet(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String[] data = result.split("=")[1].split(",");
            List<String> listData = Arrays.asList(data);
            // 股票名称
            String name = listData.get(0).replaceAll("\"","");
            // 今日开盘价
            double tsp = Double.parseDouble(listData.get(1));
            // 昨日收盘价
            double yep = Double.parseDouble(listData.get(2));
            // 当前价格
            double cp = Double.parseDouble(listData.get(3));
            // 今日最大价格
            double tmp = Double.parseDouble(listData.get(4));
            // 今日最大价格
            double tnp = Double.parseDouble(listData.get(5));
            // 日期
            String date = listData.get(30) + " " + listData.get(31);
            Double percent = (cp - yep) / yep * 100;
            Double maxPercent = (tmp - yep) / yep * 100;
            DecimalFormat df = new DecimalFormat("0.00");
            String value = df.format(percent);
            log.info("{}   {}%   {}%   {}    {}      {} ",name,value,df.format(maxPercent),cp,yep,date);
        });
        log.info("===================================================================================================");
    }




    public static void main(String[] args) {
        getStock();
    }


}
