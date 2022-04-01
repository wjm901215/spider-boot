package com.spider.ma.modules.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务示例
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.job.task.SimpleTask,v 0.1 2021/7/6 21:48 Exp $$
 */
@Component("simpleTask")
@Slf4j
public class SimpleTask {
    /**
     * 任务示例方法
     */
    public void test() {
        log.info("==============定时任务测试==============");
    }
}
