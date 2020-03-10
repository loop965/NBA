package com.yf.producer.util;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yf 2020/2/29 10:50 PM
 */
public class DownFile {

    public static void downloadNet(String videoName) throws MalformedURLException {

//        ﻿ if a < 10:
//        videoName = 'out00' + str(a) + '.ts'
//        if a < 100:
//        videoName = 'out0' + str(a) + '.ts'
//    else:
//        videoName = 'out' + str(a) + '.ts'
//        url = 'https://www.78pan.com/api/stats/hls/2018/11/29/pqAPOpps/'+videoName

        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        URL url = new URL("https://www.78pan.com/api/stats/hls/2018/11/29/pqAPOpps/" + videoName);

        try {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("/Users/private-project/" + videoName);

            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        String videoName = "";

        for (int i = 0; i <122 ; i++) {
            if (i < 10){
                videoName = "out00" + i + ".ts";
            }else if(i < 100){
                videoName = "out0" + i + ".ts";
            }else {
                videoName = "out" + i + ".ts";
            }
            downloadNet(videoName);
            System.out.println(videoName + " is done");
        }


    }
}
