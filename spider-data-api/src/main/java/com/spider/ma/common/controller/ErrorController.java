package com.spider.ma.common.controller;

import com.spider.core.common.msg.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常控制器
 * <p>过滤器异常统一由该控制器进行统一处理</p>
 *
 * @author Spiderman
 * @version $Id: com.spider.ma.common.controller.ErrorController,v 0.1 2021/07/29 20:25 Exp $$
 */
@RestController
public class ErrorController {

    /**
     * 过滤器异常统一响应
     *
     * @return
     */
    @RequestMapping(path = "/error", method = RequestMethod.POST)
    public R error(HttpServletRequest request) {
        int code = (int) request.getAttribute("code");
        String msg = (String) request.getAttribute("msg");
        return R.error(code, msg);
    }
}
