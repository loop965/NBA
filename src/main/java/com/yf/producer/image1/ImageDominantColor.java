package com.yf.producer.image1;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    private static List<MyColor> myColorList;
    private static int delete = 0;

    public static List<MyColor> getHexColor(BufferedImage image) throws IOException, InterruptedException {
        delete = 0;
        myColorList = new ArrayList<>();
        Map<Integer, Integer> colorMap = new HashMap<>(16);
        int height = image.getHeight();
        int width = image.getWidth();
        multi = height * width;
        log.info("width:{},height:{},multi:{}",width,height,multi);
//        FileWriter fileWriter = new FileWriter("E:/rgb.txt");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = image.getRGB(i, j);
                int pixel1 = image.getRGB(width-i-1, height-j-1);
//                IOUtils.write(pixel1+" ",fileWriter);
//                if (i <10 ){
//                    log.info("i:{},j:{},pixel:{},rgbArray:{}",i,j,pixel,getRGBArr(pixel));
//                }
//                if (pixel == 16842752){
//                    log.info("i:{},j:{}",i,j);
//                }
                if (pixel == 0|| pixel == 16843009 || pixel == 33686018){
                    delete++;
                    continue;
                }
                Integer counter = colorMap.get(pixel);
                if (counter == null) {
                    counter = 0;
                }
                colorMap.put(pixel, ++counter);
            }
//            IOUtils.write("\n",fileWriter);
        }

        return getMostCommonColor(colorMap);
    }

    private static List<MyColor> getMostCommonColor(Map<Integer, Integer> map) throws InterruptedException {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2)
                -> ((Comparable) obj2.getValue()).compareTo(obj1.getValue()));

        int[] rgb;
        for (int i = 0; i < 5 ; i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            rgb = getRGBArr(entry.getKey());
            int count = entry.getValue();
            String hex = "#" + Integer.toHexString(rgb[0])
                    + Integer.toHexString(rgb[1])
                    + Integer.toHexString(rgb[2]);
            log.info("pixel:{},hex:{},rgb:{}",entry.getKey(),hex,rgb);
        }

        List<Map.Entry<Integer, Integer>> list1 = combineColor(list);
        // maybe endless loop
        while (list1.size() > 1 ){
            list1 = combineColor(list1);
        }
        return myColorList;
    }

    public static List<Map.Entry<Integer, Integer>> combineColor(List<Map.Entry<Integer, Integer>> list){
        List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>();
        Map.Entry<Integer, Integer> preEntry = list.get(0);
        int[] rgb1 = getRGBArr(preEntry.getKey());
        int sum = preEntry.getValue();
        for (int j = 1; j < list.size(); j++) {
            Map.Entry<Integer, Integer> nextEntry = list.get(j);
            int[] rgb2 = getRGBArr(nextEntry.getKey());
            double deltaE = TwoColorCompare.calculateDeltaE2000(TwoColorCompare.rgbToLab(rgb1),TwoColorCompare.rgbToLab(rgb2));
            if (deltaE < 10){
                sum += nextEntry.getValue();
            } else {
                list1.add(nextEntry);
            }
        }
        preEntry.setValue(sum);
        percent = sum / (multi- delete);
        if (percent < 0.001){
            return new ArrayList<>();
        }
        String percentStr = (percent*100+"").substring(0,4)+"%";
        log.info("rgb1:{} percent:{}",rgb1,percentStr);
        MyColor myColor = new MyColor();
        myColor.setPercentStr(percentStr);
        myColor.setPercent(Double.parseDouble((percent*100+"").substring(0,4)));
        myColor.setRgb(rgb1);
        myColorList.add(myColor);
        return list1;
    }


    public static List<Map.Entry<Integer, Integer>> combineColor1(List<Map.Entry<Integer, Integer>> list) throws InterruptedException {
        List<Map.Entry<Integer, Integer>> listColl = new ArrayList<>();
        int pageSize = 300000;
        int listSize = list.size();
        int count = listSize / pageSize;
        if (listSize % pageSize != 0) {
            count++;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        Map.Entry<Integer, Integer> preEntry = list.get(0);
        int[] rgb1 = getRGBArr(preEntry.getKey());
        AtomicInteger sum = new AtomicInteger(preEntry.getValue());
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int j = 0; j < count; j++) {
            int fromIndex = j == 0 ? 1: j * pageSize;
            int toIndex = (j + 1) * pageSize;
            if (toIndex > listSize) {
                toIndex = listSize;
            }
            List<Map.Entry<Integer, Integer>> subList = list.subList(fromIndex,toIndex);
            int finalJ = j;
            executorService.execute(() -> {
                AtomicInteger sum1 = new AtomicInteger(0);
                List<Map.Entry<Integer, Integer>> list1 = new ArrayList<>();
                try {
                    subList.forEach(e ->{
                        int[] rgb2 = getRGBArr(e.getKey());
                        double deltaE = TwoColorCompare.calculateDeltaE2000(TwoColorCompare.rgbToLab(rgb1),TwoColorCompare.rgbToLab(rgb2));
                        if (deltaE < 10){
                            sum1.addAndGet(e.getValue());
                        } else {
                            list1.add(e);
                        }
                    });
                }finally {
                    countDownLatch.countDown();
                }
                sum.addAndGet(sum1.get());
                log.info("第{}次，list size:{},sum1:{}", finalJ,list1.size(),sum1.get());
                listColl.addAll(list1);
            });
        }
        countDownLatch.await();
        preEntry.setValue(sum.get());
        percent = sum.get() / (multi- delete);
        double minPercent =  0.01;
        if (percent < minPercent){
            return new ArrayList<>();
        }
        String percentStr = (percent*100+"").substring(0,4)+"%";
        log.info("rgb1:{} percent:{}",rgb1,percentStr);
        MyColor myColor = new MyColor();
        myColor.setPercentStr(percentStr);
        myColor.setPercent(Double.parseDouble((percent*100+"").substring(0,4)));
        myColor.setRgb(rgb1);
        myColorList.add(myColor);
        return listColl;
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

    public static void main(String[] args) throws IOException, InterruptedException {
        File file= new File("E:/pic1/3e497ad3471e4edcbfea6955147bc24d/3e497ad3471e4edcbfea6955147bc24d_rmg.jpg");
//        File file= new File("D:\\modoo-image\\TR170439SH111759A-BB11-12\\a76b1e51-03d6-47e9-b7e4-67ec18ff3b1c-out.png");
        BufferedImage img = ImageIO.read(file);
        long s1 =  System.currentTimeMillis();
        List<MyColor> colorList = getHexColor(img);
        long s2 =  System.currentTimeMillis();
        System.out.println("take time:"+ (s2-s1)/1000);
        colorList.sort(Comparator.comparing(MyColor::getPercent).reversed());
        CreateImageFileFromGraphicsObject.createImage(colorList,"",img);
        log.info("colorList:{}", JSONArray.toJSONString(myColorList));
        log.info("delete rgb:{}", delete);
        log.info("sum:{}",colorList.stream().mapToDouble(MyColor::getPercent).sum());
    }
}
