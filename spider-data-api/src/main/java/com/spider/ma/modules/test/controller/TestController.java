package com.spider.ma.modules.test.controller;

import com.spider.core.common.msg.R;
import com.spider.core.common.validator.ValidatorUtils;
import com.spider.ma.common.controller.BaseSupport;
import com.spider.ma.modules.test.modle.req.TestRequest;
import com.spider.ma.modules.test.modle.resp.TestResponse;
import com.spider.ma.modules.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test 控制层
 * <p>需要进拦截器必须继承BaseSupport</p>
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.controller.BaseController,v 0.1 2021/7/28 11:39 Exp $$
 */
@RestController
public class TestController extends BaseSupport {
    @Autowired
    private TestService testService;

    /**
     * TODO  基础数据接口
     * <p>@AuthIgnore 表示该接口无需用户登录就可以访问</p>
     * @return
     */
    @RequestMapping("get.village.data")
    public R getVillageData() {
        TestRequest testRequest = getInputObject(TestRequest.class);
        ValidatorUtils.validateEntity(testRequest);
        TestResponse response=testService.getVillageData(testRequest);
        return R.ok().put("data",response);
    }
}
