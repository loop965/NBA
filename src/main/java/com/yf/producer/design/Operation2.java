package com.yf.producer.design;

/**
 * @author yf 2020/8/28 11:09 PM
 */
public class Operation2 implements Operation {

    @Override
    public int add() {
        System.out.println("Operation2");
        return 0;
    }
}
