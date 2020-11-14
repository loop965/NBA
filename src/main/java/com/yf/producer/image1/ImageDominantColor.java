package com.yf.producer.image1;

import com.yf.producer.image1.CreateImageFileFromGraphicsObject;
import com.yf.producer.image1.MyColor;
import com.yf.producer.image1.TwoColorCompare;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author jittagornp
 *
 * thank you
 * http://stackoverflow.com/questions/10530426/how-can-i-find-dominant-color-of-an-image
 */

@Slf4j
public class ImageDominantColor {

    private static double multi = 0;
    private static double percent = 0;
    private static List<MyColor> myColorList = new ArrayList<>();

    public static List<MyColor> getHexColor(BufferedImage image) {
        myColorList = new ArrayList<>();
        Map<Integer, Integer> colorMap = new HashMap<>(16);
        int height = image.getHeight();
        int width = image.getWidth();
        multi = height * width;
       log.info("width:{},height:{},multi:{}",width,height,multi);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
//                if (!isGray(getRGBArr(rgb))) {
                    Integer counter = colorMap.get(rgb);
                    if (counter == null) {
                        counter = 0;
                    }

                    colorMap.put(rgb, ++counter);
                }
//            }
        }

        return getMostCommonColor(colorMap);
    }

    private static List<MyColor> getMostCommonColor(Map<Integer, Integer> map){
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2)
                -> ((Comparable) obj2.getValue()).compareTo(obj1.getValue()));

        int[] rgb = new int[0];
        for (int i = 1; i < 5 ; i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            rgb = getRGBArr(entry.getKey());
            int count = entry.getValue();
            String hex = "#" + Integer.toHexString(rgb[0])
                    + Integer.toHexString(rgb[1])
                    + Integer.toHexString(rgb[2]);
            log.info("hex:{},rgb:{}",hex,rgb);
        }

        List<Map.Entry<Integer, Integer>> list1 = combineColor(list);
//        while (list1.size() > 100){
//            list1 = combineColor(list1);
//        }
        while (percent > 0.1){
            list1 = combineColor(list1);
        }

//        return "#" + Integer.toHexString(rgb[0])
//                + Integer.toHexString(rgb[1])
//                + Integer.toHexString(rgb[2]);

        return myColorList;
    }

    public static List<Map.Entry<Integer, Integer>> combineColor(List<Map.Entry<Integer, Integer>> list){
        List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Map.Entry<Integer, Integer> entry1 = list.get(i);
            int[] rgb1 = getRGBArr(entry1.getKey());
            int sum = entry1.getValue();
            for (int j = i+1; j < list.size(); j++) {
                Map.Entry<Integer, Integer> entry2 = list.get(j);
                int[] rgb2 = getRGBArr(entry2.getKey());
//                double p = TwoColorCompare.compareColors1(rgb1,rgb2);
                double p1 = TwoColorCompare.delta(TwoColorCompare.rgbToLab(rgb1),TwoColorCompare.rgbToLab(rgb2));
                if (p1 < 10){
                    sum +=entry2.getValue();
                }else {
                    list1.add(entry2);
                }
            }
            entry1.setValue(sum);
            percent = sum/multi;
            String percentStr = (percent*100+"").substring(0,4)+"%";
            log.info("rgb1:{} percent:{}",rgb1,percentStr);
            MyColor myColor = new MyColor();
            myColor.setPercent(percentStr);
            myColor.setRgb(rgb1);
            myColorList.add(myColor);
        }
        return list1;
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
        // rembg -o /Users/yangfei/Downloads/rBOXPV4G8TiAfl4HABHI3JC4QGc528-removebg-preview.png /Users/yangfei/Downloads/rBOXPV4G8TiAfl4HABHI3JC4QGc528-removebg-preview111.png
        File file= new File("/Users/yangfei/Downloads/rBOXPV4G8TiAfl4HABHI3JC4QGc528-removebg-preview.png");
        BufferedImage img = ImageIO.read(file);
        List<MyColor> colorList = getHexColor(img);
        CreateImageFileFromGraphicsObject.createImage(colorList,"",img);
        log.info("colorList:{}",colorList);
    }
}
