package com.yf.producer.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 * @author yf 2020/3/10 2:15 PM
 */
public class MultipartFileUploader {
    public static void main(String[] args) {
        String charset = "UTF-8";
        File uploadFile1 = new File("/Users/yangfei/Downloads/WechatIMG25.jpeg");
        String requestURL = "http://39.100.112.158:8999/wechat/common/upload";

        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);

            multipart.addHeaderField("sign", "wechat-e4fbf9aca71247d8824750bd4f541c68");
            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFormField("description", "Cool Pictures");
            multipart.addFormField("keywords", "Java,upload,Spring");

            multipart.addFilePart("file", uploadFile1);


            List<String> response = multipart.finish();

            System.out.println("SERVER REPLIED:");

            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
