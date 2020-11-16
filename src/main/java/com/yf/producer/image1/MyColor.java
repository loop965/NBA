package com.yf.producer.image1;

import lombok.ToString;

/**
 * @author: yf
 * @date: 2020/11/13  10:55
 * @desc:
 */

@ToString
public class MyColor {

    private int[] rgb;

    private String percentStr;

    private double percent;

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public String getPercentStr() {
        return percentStr;
    }

    public void setPercentStr(String percentStr) {
        this.percentStr = percentStr;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
