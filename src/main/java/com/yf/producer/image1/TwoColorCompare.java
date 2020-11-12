package com.yf.producer.image1;

/**
 * @author yf 2020/11/12 8:19 下午
 */
public class TwoColorCompare {

    public static void main(String[] args) {
        int r1 = 177;
        int g1 = 238;
        int b1 = 238;
        int r2 = 187;
        int g2 = 255;
        int b2 = 255;
        System.out.println(Math.pow(2,3));
        System.out.println(Math.sqrt(9));
        compareColors(r1,g1,b1,r2,g2,b2);
    }


    public static void compareColors(int r1, int g1, int b1,int r2, int g2, int b2){
        // d=sqrt((r2-r1)^2+(g2-g1)^2+(b2-b1)^2)
        // p=d/sqrt((255)^2+(255)^2+(255)^2)
       double d = Math.sqrt(Math.pow(r2-r1,2) + Math.pow(g2-g1,2) + Math.pow(b2-b1,2));
       double a = Math.sqrt(Math.pow(255,2)*3);
       double p = d / a;
       System.out.println(p);
    }
}
