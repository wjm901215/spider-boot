package com.spider.ma.modules.test.controller;

import com.spider.core.common.msg.R;
import com.spider.ma.modules.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MQ 测试controller
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.controller.TestController,v 0.1 2021/7/31 23:14 Exp $$
 */
@RestController()
@RequestMapping("/base")
public class TestController {
    @Autowired
    private TestService testService;
    /**
     * 测试直接模式发送
     *
     * @return {"code":200,"message":"成功"}
     */
    @PostMapping("/sendDirect")
    public R sendDirect(String message) {
        testService.sendDirect(message);
        return R.ok();
    }

    /**
     * 测试主题模式发送1
     *
     * @return {"code":200,"message":"成功"}
     */
    @RequestMapping("/sendTopic1")
    public R sendTopic1(String message) {
        testService.sendTopic1(message);

        return R.ok();
    }

    /**
     * 测试主题模式发送2
     *
     * @return {"code":200,"message":"成功"}
     */
    @RequestMapping("/sendTopic2")
    public R sendTopic2(String message) {
        testService.sendTopic2(message);
        return R.ok();
    }

    /**
     * 测试延迟队列发送
     *
     * @return {"code":200,"message":"成功"}
     */
    @RequestMapping("/sendDelay")
    public R sendDelay(String message) {
        testService.sendDelay(message);
        return R.ok();
    }
}
