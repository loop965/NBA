//package com.yf.producer.google;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorModel;
//import java.io.File;
//import java.io.IOException;
//
///**
// * @author: yf
// * @date: 2020/11/09  17:42
// * @desc:
// */
//public class ColorDelete {
//
//    public static final int[][] X_SOBEL = new int[][] { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
//    public static final int[][] Y_SOBEL = new int[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
//    public static final int BLOCK_PIXEL_RADIUS = 5;
//
//    public static BufferedImage removeBlendPixels(BufferedImage image, int raidus) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        int[] pixels = new int[width * height];
//        getRGB(image, 0, 0, width, height, pixels);
//        // 创建处理结果
//        BufferedImage resultImg = createCompatibleDestImage(image, null);
//        setRGB(resultImg, 0, 0, width, height, pixels);
//        // 灰度化与梯度求取
//        byte[] grayData = getGrayData(pixels, width, height);
//        byte[] binaryData = getGrident(grayData, width, height);
//        int index = 0;
//        for (int row = 1; row < height - 1; row++) {
//            for (int col = 1; col < width - 1; col++) {
//                index = row * width + col;
//                int pixel = (binaryData[index] & 0xff);
//                if (pixel > 0) {
//                    // 半径扫描操作
//                    int mindis = Integer.MAX_VALUE;
//                    int minrow = -1;
//                    int mincol = -1;
//                    int nr = 0;
//                    int nc = 0;
//                    int index2 = 0;
//                    for (int subrow = -raidus; subrow <= raidus; subrow++) {
//                        nr = row + subrow;
//                        if (nr < 0 || nr >= height) {
//                            continue;
//                        }
//                        for (int subcol = -raidus; subcol <= raidus; subcol++) {
//                            nc = col + subcol;
//                            if (nc < 0 || nc >= width) {
//                                continue;
//                            }
//                            index2 = nr * width + nc;
//                            int value = (binaryData[index2] & 0xff);
//                            if (value == 0) {
//                                int distance = distanceColor(image.getRGB(nc, nr), image.getRGB(col, row));
//                                if (distance < mindis) {
//                                    mindis = distance;
//                                    minrow = nr;
//                                    mincol = nc;
//                                }
//                            }
//                        }
//                    }
//                    resultImg.setRGB(col, row, image.getRGB(mincol, minrow));
//                }
//            }
//        }
//        return resultImg;
//    }
//    public static int distanceColor(int rgb, int rgb2) {
//        // Color one
//        int r1 = (rgb >> 16) & 0xff;
//        int g1 = (rgb >> 8) & 0xff;
//        int b1 = rgb & 0xff;
//        // Color two
//        int r2 = (rgb2 >> 16) & 0xff;
//        int g2 = (rgb2 >> 8) & 0xff;
//        int b2 = rgb2 & 0xff;
//        // distance
//        int rr = r1 - r2;
//        int gg = g1 - g2;
//        int bb = b1 - b2;
//        int sum = (int) Math.sqrt(rr * rr + gg * gg + bb * bb);
//        return sum;
//    }
//    public static byte[] getGrayData(int[] inPixels, int width, int height) {
//        // 图像灰度化
//        byte[] outPixels = new byte[width * height];
//        int index = 0;
//        for (int row = 0; row < height; row++) {
//            int tr = 0, tg = 0, tb = 0;
//            for (int col = 0; col < width; col++) {
//                index = row * width + col;
//                tr = (inPixels[index] >> 16) & 0xff;
//                tg = (inPixels[index] >> 8) & 0xff;
//                tb = inPixels[index] & 0xff;
//                int gray = (int) (0.299 * tr + 0.587 * tg + 0.114 * tb);
//                outPixels[index] = (byte) (gray & 0xff);
//            }
//        }
//        return outPixels;
//    }
//    public static byte[] getGrident(byte[] inPixels, int width, int height) {
//        byte[] outPixels = new byte[width * height];
//        int index = 0;
//        for (int row = 0; row < height; row++) {
//            int tr = 0;
//            for (int col = 0; col < width; col++) {
//                if (row == 0 || col == 0 || (row == height - 1) || (col == width - 1)) {
//                    index = row * width + col;
//                    outPixels[index] = (byte) (0x00);
//                    continue;
//                }
//                int xg = 0, yg = 0;
//                for (int sr = -1; sr <= 1; sr++) {
//                    for (int sc = -1; sc <= 1; sc++) {
//                        int nrow = row + sr;
//                        int ncol = col + sc;
//                        if (nrow < 0 || nrow >= height) {
//                            nrow = 0;
//                        }
//                        if (ncol < 0 || ncol >= width) {
//                            ncol = 0;
//                        }
//                        index = nrow * width + ncol;
//                        tr = (inPixels[index] & 0xff);
//                        xg += X_SOBEL[sr + 1][sc + 1] * tr;
//                        yg += Y_SOBEL[sr + 1][sc + 1] * tr;
//                    }
//                }
//                index = row * width + col;
//                int g = (int) Math.sqrt(xg * xg + yg * yg);
//                outPixels[index] = (byte) (clamp(g) & 0xff);
//            }
//        }
//        return outPixels;
//    }
//
//    public static void main(String[] args) {
//        File file = new File("D:\\gloomyfish\\bigmonkey.png");
//        File resultFile = new File("D:\\gloomyfish\\result.png");
//        try {
//            BufferedImage image = ImageIO.read(file);
//            BufferedImage result = removeBlendPixels(image, BLOCK_PIXEL_RADIUS);
//            ImageIO.write(result, "png", resultFile);
//            Integer[] colors = extractColors(result);
//            System.out.println("total colors : " + colors.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static BufferedImage createCompatibleDestImage(
//            BufferedImage src, ColorModel dstCM) {
//        if ( dstCM == null )
//            dstCM = src.getColorModel();
//        return new BufferedImage(dstCM,
//                dstCM.createCompatibleWritableRaster(destW, destH),
//                dstCM.isAlphaPremultiplied(), null);
//    }
//
//}
