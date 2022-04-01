package com.spider.ma.modules.test.modle.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 测试响应实体
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.test.modle.resp.TestResponse,v 0.1 2021/08/08 16:51 Exp $$
 */
@Data
@Builder
public class TestResponse {
    private List<Village> list;
    @Data
    @Builder
    public static class Village{
        /**
         * 小区名称
         */
        private String villName;
        /**
         * 小区编码
         */
        private String villCode;
        /**
         * 地址
         */
        private String address;
    }
}
