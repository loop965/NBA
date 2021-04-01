package com.yf.producer;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.yf.producer.dao.modoo.ModooMapper;
import com.yf.producer.image1.ImageDominantColor;
import com.yf.producer.image1.CreateImageFileFromGraphicsObject;
import com.yf.producer.image1.MyColor;
import com.yf.producer.pojo.BrdProduct;
import com.yf.producer.service.InsertDataService;
import com.yf.producer.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProducerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private InsertDataService selectDataService;

    @Autowired
    private ModooMapper modooMapper;

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

    @Test
    public void colorTest(){
        List<BrdProduct> productList = modooMapper.selectBrdProductList();
        productList.forEach(brdProduct -> {
            String id = brdProduct.getId();
            String imagePath = brdProduct.getProductImage();
            if (StringUtils.isBlank(imagePath)){
                return;
            }
            String tempPath = "D:/modoo-image" + "/" + id;
            File file = new File(tempPath);
            if (!file.exists()){
                boolean flag = file.mkdirs();
                if (!flag){
                    log.warn("创建文件夹失败tempPath={}",tempPath);
                    return;
                }
            }
            String fileName = UUID.fastUUID().toString();
            String picturePath = tempPath + "/" + fileName + ".jpg";
            File pictureFile = new File(picturePath);
            try {
                HttpUtil.downloadFile(imagePath,pictureFile);
                String rembgPng =  tempPath + "/" + fileName + "-out.png";
                Process process = Runtime.getRuntime().exec("rembg -o " + rembgPng + " " + picturePath);
                printResults(process);
                BufferedImage img = ImageIO.read(new File(rembgPng));
                List<MyColor> colorList = ImageDominantColor.getHexColor(img);
                colorList.sort(Comparator.comparing(MyColor::getPercent).reversed());
                if (colorList.size() == 0){
                    System.out.println(colorList);
                    return;
                }
                CreateImageFileFromGraphicsObject.createImage(colorList,tempPath + "/",img);
                log.info("colorList:{}",colorList);
                log.info("sum:{}",colorList.stream().mapToDouble(MyColor::getPercent).sum());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


}
