package com.yf.producer.image1;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author yf 2020/11/12 9:24 下午
 */
public class CreateImageFileFromGraphicsObject {

    public static void main(String[] args) throws IOException {

    }

    public static void createImage(List<MyColor> colorList,String path,BufferedImage img) throws IOException {
        int size = colorList.size();

        int width = 200 * (size);
        int height = 250;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(Color.lightGray);
//        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height);
//        g2d.drawImage(img,0,0,200,250,null);


        for (int i = 0;i < size; i++) {
            int[] rgb = colorList.get(i).getRgb();
            String percent = colorList.get(i).getPercentStr();
            // create a circle with black
            g2d.setColor(new Color(rgb[0],rgb[1],rgb[2]));
            g2d.fillOval((i) * 200, 0, 200, 200);

            // create a string with yellow
            g2d.setColor(Color.red);
            g2d.drawString(percent, 85 + (i)* 200, 225);
        }
        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = new File(path + "myimage10.png");
        ImageIO.write(bufferedImage, "png", file);

        // Save as JPEG
//        file = new File(path + "/myimage.jpg");
//        ImageIO.write(bufferedImage, "jpg", file);

    }
}
