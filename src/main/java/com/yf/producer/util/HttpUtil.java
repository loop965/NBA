package com.yf.producer.util;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author: yf
 * @date: 2020/03/12  09:35
 * @desc:
 */
@Slf4j
public class HttpUtil {

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\tiger_rose_picture" + File.separator +  UUIDGenerator.getUUID();
        File file = new File(filePath);
        file.mkdirs();
        String picturePath = filePath + File.separator + UUIDGenerator.getUUID() + ".jpg";
        File pictureFile =  new File(picturePath);
        String downloadUrl = "https://www.tigersrose.com//group1/M00/01/A9/rBOXPV4CC1yAbYOwABeRkszg3NQ782_240x320.jpg";
        downloadFile(downloadUrl,pictureFile);
        String url = "http://39.100.112.158:8999/wechat/common/upload";
        String path = uploadPicture(picturePath,url);
        System.out.println(path);
    }

    /**
     * fastdfs上传文件
     * @param localFile 本地文件路径
     * @param url 上传文件接口url
     * @return string
     */
    private static String uploadFile(String localFile, String url){
        String result = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(url);
            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(localFile));
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("file", bin)
                    .build();
            httpPost.setEntity(reqEntity);
            // todo 获取sign
            httpPost.setHeader("sign","wechat-e4fbf9aca71247d8824750bd4f541c68");
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            System.out.println("The response value of token:" + response.getFirstHeader("token"));
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应内容
                result = EntityUtils.toString(resEntity, StandardCharsets.UTF_8);
            }
            // 销毁
            EntityUtils.consume(resEntity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String uploadPicture(String filePath,String uploadUrl){
        String storePath = null;
        String result = uploadFile(filePath,uploadUrl);
        if (StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (Objects.equals(jsonObject.getInteger("code"),200)){
                storePath = jsonObject.getString("data");
                log.info("上传图片成功路劲：{}",storePath);
            }
        }
        log.info("上传图片结果：{},原图片路劲: {}",result,filePath);
        return storePath;
    }


    /**
     * 下载文件
     * @param downloadUrl 文件地址
     * @param file 本地文件
     */
    public static void downloadFile(String downloadUrl,File file) throws Exception{

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        URL url = new URL(downloadUrl);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        int length = 0;
        byte[] bytes = new byte[1024];
        while ((length = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
        }
        fileOutputStream.close();
        inputStream.close();

    }


}
