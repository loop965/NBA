package com.yf.producer.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yf
 * @date: 2020/05/19  10:51
 * @desc:
 */
@Slf4j
public class TreeMenuTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<String> list = new ArrayList<>();
        int count = 1000;
        for (int i = 0; i < count ; i++) {
            int finalI = i;
            executorService.execute(new Thread(() -> {
                synchronized (list){
                    list.add("s-" + finalI);
                }
            }));
        }
        executorService.shutdown();
        System.out.println(list.size());
        list.parallelStream().forEach(e -> log.info("{}",e));
    }

}
