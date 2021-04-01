package com.yf.producer;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yf
 * @date: 2020/10/26  14:51
 * @desc:
 */
public class Test1 {
    public static void main(String[] args) {
        ExcelReader ex = ExcelUtil.getReader(new File("E://电商店铺服务数据.xlsx"),1);
        Sheet sheet = ex.getSheet();
        List<Integer> lists = new ArrayList<>();
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 1000 ; i++) {
            lists.add(i);
        }
        System.out.println(lists.size());
        System.out.println("dd");
    }
}
