package com.yf.producer.image1;

import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyColor)) return false;
        MyColor myColor = (MyColor) o;
        return Double.compare(myColor.getPercent(), getPercent()) == 0 &&
                Arrays.equals(getRgb(), myColor.getRgb()) &&
                getPercentStr().equals(myColor.getPercentStr());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getPercentStr(), getPercent());
        result = 31 * result + Arrays.hashCode(getRgb());
        return result;
    }


}
