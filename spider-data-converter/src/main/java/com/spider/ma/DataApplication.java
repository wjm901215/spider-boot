package com.spider.ma;

import com.spider.core.common.spring.UxInitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统启动类
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.DataApplication,v 0.1 2021/7/29 22:15 Exp $$
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.spider.core.common","com.spider.ma"})
public class DataApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DataApplication.class);
        app.addInitializers(new UxInitializingBean());
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DataApplication.class);
    }
}
