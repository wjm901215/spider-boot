package com.spider.ma.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * created by zhanweijie when 2021/7/13
 * email professorX@gmail.com
 */

public class MapUtil {

    private MapUtil() {

    }

    public static Map filterMapNotNull(Map map) {
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        Map result = new HashMap();
        while (iterator.hasNext()) {
            Map.Entry next = iterator.next();
            System.out.println(next.getValue());
            if (null == next.getValue() || "".equals(next.getValue())) {
                continue;
            }
            result.put(next.getKey(), next.getValue());
        }
        return result;
    }
}
