package com.yf.producer.image1;

import java.io.File;
import java.io.IOException;

/**
 * @author: yf
 * @date: 2020/11/19  15:59
 * @desc:
 */
public class ReadDir {

    public static void main(String[] args) throws IOException {
        readDir("D:/moodo-image1");
    }


    public static void readDir(String path) throws IOException {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File item : files) {
            if (item.isDirectory()){
                readDir(item.getAbsolutePath());
            }else {
                String getAbsolutePath = item.getAbsolutePath();
                String fileName = item.getName();
                String outPng = item.getParent() + "/" + fileName.split("[.]")[0] + "-out.png";
                Process process = Runtime.getRuntime().exec("rembg -o " + outPng + " " + getAbsolutePath);
//                RemoveBackground.printResults(process);
            }
        }
    }




}
