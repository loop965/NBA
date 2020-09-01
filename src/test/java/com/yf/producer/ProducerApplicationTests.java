package com.yf.producer;

import com.alibaba.fastjson.JSONObject;
import com.yf.producer.pojo.BrdProduct;
import com.yf.producer.service.InsertDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private InsertDataService selectDataService;

    @Test
    public void contextLoads() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("memberId","0015453f98324b9897b5ce40c2a16d29");
        jsonObject.put("sign","0015453f98324b9897b5ce40c2a16d292");
        redisTemplate.opsForValue().set("screen_99",jsonObject);



        Set<String> stringSet = new HashSet<>();
        stringSet.add("spring");
        redisTemplate.opsForValue().set("set",stringSet);
        Set<String> stringSet1 = (Set<String>) redisTemplate.opsForValue().get("set");
        System.out.println(stringSet1);

        redisTemplate.opsForSet().add("set1","s2");
        redisTemplate.opsForSet().add("set1","s1");
        redisTemplate.opsForSet().add("set1","s1");
        Set<String> stringSet11 = (Set<String>)redisTemplate.opsForSet().members("set1");

        redisTemplate.opsForList().leftPushAll("list","l","l2","l3");
        String list = (String) redisTemplate.opsForList().leftPop("list");


        File file1 = new File("D:\\log-924958.jpg");

        File file = new File("http://img3.imgtn.bdimg.com/it/u=1267490802,3326052181&fm=26&gp=0.jpg");
        System.out.println();


    }

    @Test
    public void TestData(){
//        BrdProduct newBrdProduct = selectDataService.selectBrdProduct("105808", Constant.TIGER_BID);
//        BrdProductStock updateBrdProductStock = new BrdProductStock();
//        updateBrdProductStock.setProductId("008c5c77-3108-4e51-b217-cd48ca4784e4");
//        updateBrdProductStock.setSourceSid(newBrdProduct.getShopId());
//        updateBrdProductStock.setStock(newBrdProduct.getInStock());
//        updateBrdProductStock.setUpdateTime(newBrdProduct.getUpdateTime());
//        updateBrdProductStock.setUpdateUser(newBrdProduct.getUpdateUser());
//        selectDataService.updateBrdProductStock(updateBrdProductStock);
    }

    @Test
    public void testIot(){
        BrdProduct brdProduct =selectDataService.selectBrdProduct("1","1");
        System.out.println(brdProduct);
    }

}
