package com.yf.producer.task;

import com.yf.producer.util.HttpClientUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author: yf
 * @date: 2019/12/31  09:12
 * @desc:
 */
@Slf4j
public class ExistThread implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        while (true){
            NBAScoreTask.exitCode = "e";
            Scanner existScanner = new Scanner(System.in);
            String code = existScanner.next();
            if ("q".equals(code)){
                NBAScoreTask.exitCode = code;
                break;
            }
        }
    }



}
