package com.yf.producer;

import com.alibaba.fastjson.JSONObject;
import com.yf.producer.dao.modoo.InsertDataMapper;
import com.yf.producer.dao.modoo.ModooMapper;
import com.yf.producer.dao.tigerrose.TigerMapper;
import com.yf.producer.pojo.BrdOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class ProducerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private InsertDataService selectDataService;
//
    @Autowired
    private InsertDataMapper dataMapper;

    @Autowired
    private ModooMapper modooMapper;

    @Autowired
    private TigerMapper tigerMapper;

    @Test
    void contextLoads() {

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
    void TestData(){
        List<BrdOrder> orders = dataMapper.testOneToMore();
        System.out.println(orders.size());
    }

    @Test
    void testDataSource(){
        String role1 = tigerMapper.selectRoleName();
        String role = modooMapper.selectRoleName();
        System.out.println(role);
    }

}
