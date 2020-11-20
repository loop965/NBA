package com.yf.producer.image1;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
    private static int de = 0;

    public static List<MyColor> getHexColor(BufferedImage image) {
        myColorList = new ArrayList<>();
        Map<Integer, Integer> colorMap = new HashMap<>(16);
        int height = image.getHeight();
        int width = image.getWidth();
        multi = height * width;
       log.info("width:{},height:{},multi:{}",width,height,multi);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                if (i == 0){
                    log.info("pixel:{},rgbArray:{}",pixel,getRGBArr(pixel));
                }
//                if (rgb == 0){
//                    de++;
//                    continue;
//                }
                Integer counter = colorMap.get(pixel);
                if (counter == null) {
                        counter = 0;
                }
                colorMap.put(pixel, ++counter);
            }
        }

        return getMostCommonColor(colorMap);
    }

    private static List<MyColor> getMostCommonColor(Map<Integer, Integer> map){
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, (Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2)
                -> ((Comparable) obj2.getValue()).compareTo(obj1.getValue()));

        int[] rgb;
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
        // maybe endless loop
        while (list1.size() > 1 ){
            list1 = combineColor(list1);
        }
//        while (percent > 0.1){
//            list1 = combineColor(list1);
//        }
        return myColorList;
    }

    public static List<Map.Entry<Integer, Integer>> combineColor(List<Map.Entry<Integer, Integer>> list){
        List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Map.Entry<Integer, Integer> preEntry = list.get(i);
            int[] rgb1 = getRGBArr(preEntry.getKey());
            int sum = preEntry.getValue();
            for (int j = i+1; j < list.size(); j++) {
                Map.Entry<Integer, Integer> nextEntry = list.get(j);
                int[] rgb2 = getRGBArr(nextEntry.getKey());
                double deltaE = TwoColorCompare.calculateDeltaE2000(TwoColorCompare.rgbToLab(rgb1),TwoColorCompare.rgbToLab(rgb2));
                if (deltaE < 21){
                    sum += nextEntry.getValue();
                } else {
                    list1.add(nextEntry);
                }
            }
            preEntry.setValue(sum);
            percent = sum / (multi-de);
            if (percent < 0.001){
                continue;
            }
            String percentStr = (percent*100+"").substring(0,4)+"%";
            log.info("rgb1:{} percent:{}",rgb1,percentStr);
            MyColor myColor = new MyColor();
            myColor.setPercentStr(percentStr);
            myColor.setPercent(Double.parseDouble((percent*100+"").substring(0,4)));
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
        File file= new File("D:\\modoo-image\\TR190690OC112979D-RR14-12\\784f36fd-39e9-46fb-94f7-b8a645170aa9.out.out.png");
//        File file= new File("D:\\modoo-image\\TR190129TS113198A-YG12-12\\827636e1-138f-46a9-927d-0041dff99a98.out.png");
        BufferedImage img = ImageIO.read(file);
        List<MyColor> colorList = getHexColor(img);
        colorList.sort(Comparator.comparing(MyColor::getPercent).reversed());
        CreateImageFileFromGraphicsObject.createImage(colorList,"",img);

        log.info("colorList:{}",colorList);
        log.info("del:{}",de);
        log.info("sum:{}",colorList.stream().mapToDouble(MyColor::getPercent).sum());
    }
}
