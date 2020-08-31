package com.yf.producer.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yf 2020/8/28 11:10 PM
 */
public class Context {

    public static Map<String,Operation> map = new HashMap<>();

    static {
        map.put("1",new Operation1());
        map.put("2",new Operation1());
    }
}
