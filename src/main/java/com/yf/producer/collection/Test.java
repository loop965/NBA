package com.yf.producer.collection;

/**
 * @author: yf
 * @date: 2020/03/26  13:40
 * @desc:
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MyLinkedList list = new MyLinkedList();
        list.add("s");
        list.add("s1");
        list.add("s2");
        System.out.println();
        list.removeLast();
    }
}
