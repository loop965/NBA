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

    public static void createImage(List<MyColor> colorList,String path) throws IOException {
        int size = colorList.size();

        int width = 250*size;
        int height = 250;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();


        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        for (int i = 0; i < size; i++) {
            int[] rgb = colorList.get(i).getRgb();
            String percent = colorList.get(i).getPercent();
            // create a circle with black
            g2d.setColor(new Color(rgb[0],rgb[1],rgb[2]));
            g2d.fillOval(i * 250, 0, 250, height);

            // create a string with yellow
            g2d.setColor(Color.yellow);
            g2d.drawString(percent, 115+i*250, 120);
        }
        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = new File(path + "myimage1.png");
        ImageIO.write(bufferedImage, "png", file);

        // Save as JPEG
//        file = new File(path + "/myimage.jpg");
//        ImageIO.write(bufferedImage, "jpg", file);

    }
}
