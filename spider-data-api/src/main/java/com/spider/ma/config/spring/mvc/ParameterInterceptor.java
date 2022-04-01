package com.spider.ma.config.spring.mvc;


import com.spider.core.common.base.RqsBody;
import com.spider.core.common.constant.Constant.RspEnum;
import com.spider.core.common.exception.BusinessException;
import com.spider.core.common.utils.FastJsonUtil;
import com.spider.core.common.utils.MD5Sign;
import com.spider.ma.common.constant.BasicConstants;
import com.spider.ma.common.controller.BaseSupport;
import com.spider.ma.common.service.AppKeyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 接口请求拦截器
 * <p>验签、参数解析</p>
 *
 * @author Spiderman
 * @version $Id: com.spider.ma.config.spring.mvc.GatewayInterceptor,v 0.1 2021/07/28  11:44 Exp $$
 */
@Configuration
public class ParameterInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(ParameterInterceptor.class);
    @Autowired
    private AppKeyService appKeyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod method = (HandlerMethod) handler;
        Object bean = method.getBean();
        if (bean instanceof BaseSupport) {
            BaseSupport controller = (BaseSupport) bean;
            RqsBody rqsBody = (RqsBody) request.getAttribute(BasicConstants.RQS_BODY);
            String appKey = appKeyService.getKeyByAppId(rqsBody.getAppid());
            if (StringUtils.isEmpty(appKey)) {
                logger.error("无法根据app_id找到appKey：" + rqsBody.getAppid());
                throw new BusinessException(RspEnum.APP_ID_FAIL.value, RspEnum.APP_ID_FAIL.key);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(rqsBody.getAppid()).append(rqsBody.getMethod()).append(rqsBody.getTimestamp());
            Map dataMap = FastJsonUtil.json2Map(rqsBody.getData());
            List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(dataMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, Comparator.comparing(o -> (o.getKey())));
            for (Map.Entry<String, Object> tt : infoIds) {
                stringBuilder.append(tt.getValue());
            }
            stringBuilder.append(appKey);
            String sign = MD5Sign.md5(stringBuilder.toString());
            if (!rqsBody.getSign().equals(sign)) {
                logger.error("签名认证失败,请求参数：" + rqsBody.toString() + "\n 后台生成签名：" + sign);
                throw new BusinessException(RspEnum.SIGN_FAIL.value, RspEnum.SIGN_FAIL.key);
            }
            controller.putInputJson(rqsBody.getData());
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HandlerMethod method = (HandlerMethod) handler;
        Object bean = method.getBean();
        if (bean instanceof BaseSupport) {
            BaseSupport controller = (BaseSupport) bean;
            controller.removeInputJson();
            logger.info("clean input json");
        }
        logger.info("afterCompletion");
    }
}
