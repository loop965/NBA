package com.yf.producer.study.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author: yf
 * @date: 2020/08/20  17:00
 * @desc:
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(){
            @Override
            public void run(){
                while(true){
//                    System.out.println("未被中断");
                    if (this.isInterrupted()){
                        System.out.println("线程中断");
                        break;
                    }
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();

        /**
         * 输出结果(无限执行):
         未被中断
         未被中断
         未被中断
         ......
         */
    }
}
