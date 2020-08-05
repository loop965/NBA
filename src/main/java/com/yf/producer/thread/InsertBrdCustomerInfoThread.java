package com.yf.producer.thread;

import com.google.gson.reflect.TypeToken;
import com.yf.producer.pojo.BrdProduct;
import com.yf.producer.pojo.Constant;
import com.yf.producer.service.InsertDataService;
import com.yf.producer.util.GsonUtils;
import com.yf.producer.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yf
 * @create: 2019/11/21  10:06
 */
@Slf4j
public class InsertBrdCustomerInfoThread implements Runnable {

    private static InsertDataService selectDataService = SpringContextHolder.getBean(InsertDataService.class);

    @Override
    public void run() {
        log.info("===== multi thread begin insert or update product =====");
        long s1 = System.currentTimeMillis();
        AtomicInteger insetNum = new AtomicInteger();
        AtomicInteger updateNum = new AtomicInteger();
        List<BrdProduct> list = GsonUtils.fromJson(data, new TypeToken<List<BrdProduct>>(){}.getType());
        if (list == null){
            return;
        }
        list.parallelStream().forEach(newBrdProduct -> {
            String uid = newBrdProduct.getUid();
            BrdProduct brdProduct = selectDataService.selectBrdProduct(uid, Constant.TIGER_BID);
            if (brdProduct == null){
                insetNum.getAndIncrement();
                newBrdProduct.setBid(Constant.TIGER_BID);
                newBrdProduct.setId(UUID.randomUUID().toString());
                selectDataService.insertBrdProduct(newBrdProduct);
            }else {
                Date updateTime = brdProduct.getUpdateTime();
                Date newUpdateTime = newBrdProduct.getUpdateTime();
                if (updateTime == null || newUpdateTime == null || newUpdateTime.compareTo(updateTime) > 0){
                    updateNum.getAndIncrement();
                    newBrdProduct.setId(brdProduct.getId());
                    selectDataService.updateBrdProduct(newBrdProduct);
                }
            }
        });
        long s2 = System.currentTimeMillis();
        log.info("===== multi thread table brd_product insert {}条，update {}条，耗时：{}s =====",insetNum,updateNum,(s2 - s1)/1000);
    }

    private String data;

    public InsertBrdCustomerInfoThread(String data){
        this.data = data;
    };


    public static void main(String[] args) {
        InsertBrdCustomerInfoThread insertBrdCustomerInfoThread = new InsertBrdCustomerInfoThread("");
        Thread thread = new Thread(insertBrdCustomerInfoThread);
        thread.start();
    }
}
