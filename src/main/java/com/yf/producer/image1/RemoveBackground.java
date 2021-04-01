package com.yf.producer.image1;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: yf
 * @date: 2020/11/17  09:39
 * @desc:
 */

@Slf4j
public class RemoveBackground {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedImage bufferedImage = ImageIO.read(new File("D:\\modoo-image\\TR170439SH111759A-BB11-12\\a76b1e51-03d6-47e9-b7e4-67ec18ff3b1c.jpg"));
        int[] rgb2 = new int[]{252,89,161};
        removeBg(bufferedImage,rgb2);
//        Process process = Runtime.getRuntime().exec("rembg -p D:/modoo-image/test3");
//        process.waitFor();
//        System.out.println("ddd");
//        printResults(process);
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


    public static void removeBg(BufferedImage image,int[] rgb2) throws IOException {
        int height = image.getHeight();
        int width = image.getWidth();
        int multi = height * width;
        log.info("width:{},height:{},multi:{}",width,height,multi);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                int[] rgb1 = getRGBArr(pixel);
                double deltaE = TwoColorCompare.calculateDeltaE2000(TwoColorCompare.rgbToLab(rgb1),TwoColorCompare.rgbToLab(rgb2));
                if (deltaE < 25){
                    image.setRGB(i,j,0xffffff);
                }
            }
        }

        File file = new File("remove.png");
        ImageIO.write(image,"png",file);
    }


    private static int[] getRGBArr(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new int[]{red, green, blue};
    }


}
