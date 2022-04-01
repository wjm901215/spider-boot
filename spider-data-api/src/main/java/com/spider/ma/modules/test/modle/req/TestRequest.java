package com.spider.ma.modules.test.modle.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 测试请求实体类
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.test.modle.resp.TestRequest,v 0.1 2021/08/08 16:51 Exp $$
 */
@Data
@ToString
public class TestRequest {
    /**
     * 页码
     */
    @NotNull(message = "页码为必填项")
    private Integer pageNo;
    /**
     * 每页记录
     */
    @NotNull(message = "每页记录为必填项")
    private Integer pageSize;
}
