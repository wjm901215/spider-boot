package com.spider.ma.common.controller;

import com.spider.core.common.utils.FastJsonUtil;
import com.spider.core.common.utils.ThreadVariable;

/**
 * controller基础类
 *
 * @author Spiderman
 * @version $Id: com.tettek.service.common.spring.mvc.GatewayInterceptor,v 0.1 2018/7/17 14:09 Exp $$
 */
public class BaseSupport {
    /**
     * JSON key
     */
    private static final String _INPUT_JSON="_INPUT_JSON";

    protected <T> T getInputObject(Class<T> tClass) {
        return FastJsonUtil.getObject((String) ThreadVariable.getInstance().get(_INPUT_JSON),tClass);
    }

    public void putInputJson(String json) {
        ThreadVariable.getInstance().put(_INPUT_JSON, json);
    }

    public void removeInputJson(){
        ThreadVariable.getInstance().remove(_INPUT_JSON);
    }

}
