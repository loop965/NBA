package com.yf.producer.google;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jittagornp
 *
 * thank you
 * http://stackoverflow.com/questions/10530426/how-can-i-find-dominant-color-of-an-image
 */
public class ImageDominantColor {

    public static String getHexColor(BufferedImage image) {

        Map<Integer, Integer> colorMap = new HashMap<>(16);
        int height = image.getHeight();
        int width = image.getWidth();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                if (!isGray(getRGBArr(rgb))) {
                    Integer counter = colorMap.get(rgb);
                    if (counter == null) {
                        counter = 0;
                    }

                    colorMap.put(rgb, ++counter);
                }
            }
        }

        return getMostCommonColor(colorMap);
    }

    private static String getMostCommonColor(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2)
                -> ((Comparable) obj2.getValue()).compareTo(obj1.getValue()));

        int[] rgb = new int[0];
        for (int i = 1; i < 5 ; i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            rgb = getRGBArr(entry.getKey());
            int count = entry.getValue();
            System.out.println("k:"+ rgb[0] + ":" + rgb[1] + ":" +rgb[2] +",v:" + count);
            String rgb1 = "#" + Integer.toHexString(rgb[0])
                    + Integer.toHexString(rgb[1])
                    + Integer.toHexString(rgb[2]);
            System.out.println(rgb1);
        }


        return "#" + Integer.toHexString(rgb[0])
                + Integer.toHexString(rgb[1])
                + Integer.toHexString(rgb[2]);
    }

    private static int[] getRGBArr(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        
        return new int[]{red, green, blue};
    }

    private static boolean isGray(int[] rgbArr) {
        int rgDiff = rgbArr[0] - rgbArr[1];
        int rbDiff = rgbArr[0] - rgbArr[2];
        // Filter out black, white and grays...... (tolerance within 10 pixels)
        int tolerance = 5;
        if (rgDiff > tolerance || rgDiff < -tolerance) {
            if (rbDiff > tolerance || rbDiff < -tolerance) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
//        File file= new File("D:\\image\\tigerrose\\38880\\a8ac39fc20024453803afee2c35b3e0a.jpg");
        File file= new File("D:\\image\\tigerrose\\38879\\ec828331d88542b2a82ad7c76e4b1421.jpg");
        BufferedImage img = ImageIO.read(file);
        String hex = getHexColor(img);
        System.out.println(hex);
    }
}
