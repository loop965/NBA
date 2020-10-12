package com.yf.producer.collection;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: yf
 * @date: 2020/09/10  17:20
 * @desc:
 */
public class BlockQueue {
    public static void main(String[] args) {

        ArrayBlockingQueue<String> list = new ArrayBlockingQueue<>(1000);
        for (int i = 0; i < 1000; i++) {
            list.add(i+"");
        }
        System.out.println(list.size());
    }
}
