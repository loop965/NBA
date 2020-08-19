//package com.yf.producer.util;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * @author: yf
// * @date: 2020/01/21  14:18
// * @desc:
// */
//public class ImageUtil {
//
//    /** 水印文字内容 */
//    public static final String MARK_TEXT = "妙手空空";
//    /** 水印文字类型 */
//    public static final String FONT_NAME = "微软雅黑";
//    /** 水印文字样式 */
//    public static final int FONT_STYLE = Font.BOLD;
//    /** 水印文字大小 */
//    public static final int FONT_SIZE= 120;// 单位:像素
//    /** 水印文字颜色 */
//    public static final Color FONT_COLOR= Color.BLACK;
//    /** 水印文字位置X轴 */
//    public static final int X = 10;
//    /** 水印文字位置Y轴 */
//    public static final int Y = 10;
//    /** 水印文字透明度*/
//    public static final float ALPHA = 0.3F;
//
//    /** 水印图片*/
//    public static final String LOGO = "logo.png";
//
//    public static void readImage() throws Exception {
//        String realUploadPath = "D:\\我的坚果云\\壁纸\\";
//        String logoFileName = "logo_" + "dwedf.jpg";
//        OutputStream os = null;
//        File imageFile = new File("D:\\我的坚果云\\壁纸\\photo-1502082553048-f009c37129b9.jpg");
//        BufferedImage bufferedImage = ImageIO.read(imageFile);
//        int height = bufferedImage.getHeight();
//        int width = bufferedImage.getWidth();
//
//        BufferedImage bufferedImage1 = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = bufferedImage1.createGraphics();
//        g.drawImage(bufferedImage,0,0,width,height,null);
//
//        g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
//        g.setColor(FONT_COLOR);
//        int markWidth = FONT_SIZE * getTextLength(MARK_TEXT);
//        int markHeight = FONT_SIZE;
//        // 水印的高度和宽度之差
//        int widthDiff = width - markWidth;
//        int heightDiff = height - markHeight;
//
//        int x = X;
//        int y = Y;
//
//        // 判断设置的值是否大于图片大小
//        if(x > widthDiff){
//            x = widthDiff;
//        }
//        if(y > heightDiff){
//            y =heightDiff;
//        }
//
//        // 设置水印文字透明度
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
//
//        int xmove = 200;// 水印之间的间隔
//        int ymove = 200;// 水印之间的间隔
//
//        // 循环添加
//        while (x < width * 1.5){
//            y = -height / 2;
//            while(y < height * 1.5){
//                g.drawString(MARK_TEXT, x, y);
//
//                y += markHeight + ymove;
//            }
//            x += markWidth + xmove;
//        }
//
//
//        g.dispose();
//
//        os = new FileOutputStream(realUploadPath + "/" + logoFileName);
//        JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//        en.encode(bufferedImage);
//        try {
//            os.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static int getTextLength(String text){
//        int length = text.length();
//        for(int i = 0 ; i < text.length(); i++){
//            String s = String.valueOf(text.charAt(i));
//            if(s.getBytes().length > 1){
//                length++;
//            }
//        }
//        length = length % 2 == 0 ? length / 2 : length / 2 + 1;
//        return length;
//    }
//
//    public static void main(String[] args) throws Exception {
//        readImage();
//    }
//
//
//
//
//
//}
