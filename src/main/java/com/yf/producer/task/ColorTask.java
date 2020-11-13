package com.yf.producer.task;

import com.yf.producer.dao.modoo.ModooMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yf
 * @date: 2020/11/13  11:47
 * @desc:
 */
@Component
@Slf4j
public class ColorTask {

    @Autowired
    private ModooMapper modooMapper;

//    @Scheduled(initialDelay = 1000 * 3, fixedDelay=Long.MAX_VALUE)
    public void colorDetect(){

    }
}
