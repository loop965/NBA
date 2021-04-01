package com.yf.producer.study;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: yf
 * @date: 2021/01/05  16:06
 * @desc:
 */
public class UploadPicture {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Documents\\WXWork\\1688854037492608\\Cache\\File\\2021-01\\LK全部图片\\LK全部图片");
        File[] files = file.listFiles();
        Set<Integer> lengthSet = new HashSet<>();
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            String originalName =  fileName.split("[.]")[0];
            lengthSet.add(originalName.length());
            if (originalName.length() > 7){
                System.out.println(originalName);
            }

        }
        System.out.println();
    }
}
