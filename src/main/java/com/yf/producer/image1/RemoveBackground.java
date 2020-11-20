package com.yf.producer.image1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: yf
 * @date: 2020/11/17  09:39
 * @desc:
 */
public class RemoveBackground {
    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("rembg -p D:/modoo-image/test3");
        printResults(process);
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
