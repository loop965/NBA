package com.yf.producer.google;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;

/**
 * @author: yf
 * @date: 2020/11/09  11:00
 * @desc:
 */
public class ImageColor {

        public static void main(String args[])throws Exception {
            FileWriter writer = new FileWriter("D:\\image\\pixel_values1.txt");
            //Reading the image
            File file= new File("D:\\image\\tigerrose\\38879\\ec828331d88542b2a82ad7c76e4b1421.jpg");
            BufferedImage img = ImageIO.read(file);
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    //Retrieving contents of a pixel
                    int pixel = img.getRGB(x,y);
                    //Creating a Color object from pixel value
                    Color color = new Color(pixel, true);
                    //Retrieving the R G B values
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    writer.append(red+":");
                    writer.append(green+":");
                    writer.append(blue+"");
                    writer.append("\n");
                    writer.flush();
                }
            }
            writer.close();
            System.out.println("RGB values at each pixel are stored in the specified file");
        }


}
