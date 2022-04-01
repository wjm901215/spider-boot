package com.spider.ma;

import com.spider.core.common.utils.FastJsonUtil;
import com.spider.core.common.utils.MD5Sign;

import java.util.*;

/**
 * 接口请求内容组装
 * <p>具体说明</p>
 *
 * @author Spiderman
 * @version $Id: com.tettek.AsciiTest,v 0.1 2019/08/27 09:20 Exp $$
 */
public class AsciiTest {

    static String appId = "93f83219ff";
    static String securityKey = "EGfvomj+eVE=";
    static String timestamp = "20210728130754";

    public static void main(String[] args) {
        String paramStr = getVillageDataWrapper();
        System.out.println("\n" + paramStr);
    }

    /**
     * 登录请求体组装
     * @return
     */
    private static String getVillageDataWrapper() {
        String method = "get.village.data";
        Map paramMap = new HashMap(4) {{
            put("pageNo", "1");
            put("pageSize", "10");
        }};
        List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(paramMap.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        Collections.sort(infoIds, Comparator.comparing(o -> (o.getKey())));
        String cont = "";
        for (Map.Entry<String, Object> tt : infoIds) {
            System.out.println(tt.getKey() + " " + tt.getValue());
            cont += tt.getValue();
        }
        String content = appId + method + timestamp + cont + securityKey;
        String sign = MD5Sign.md5(content);
        System.out.println("待加密内容串:" + content);
        Map param = new HashMap(8) {{
            put("appid", appId);
            put("method", method);
            put("timestamp", timestamp);
            put("sign", sign);
            put("data", paramMap);
        }};
        return FastJsonUtil.toJSONString(param);
    }


}
