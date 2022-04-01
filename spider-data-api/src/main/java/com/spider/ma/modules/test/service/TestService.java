package com.spider.ma.modules.test.service;

import com.spider.ma.modules.test.modle.req.TestRequest;
import com.spider.ma.modules.test.modle.resp.TestResponse;

/**
 * 测试Service接口
 * <p>具体说明</p>
 *
 * @author SpiderMan
 * @version : com.spider.ma.modules.base.service.TestService,v 0.1 2021/7/28 11:58 Exp $$
 */
public interface TestService {

    /**
     * 获取小区数据
     * @param testRequest 请求实体
     * @return 响应实体
     */
    TestResponse getVillageData(TestRequest testRequest);
}
