package com.spider.ma.config.filter;

import com.spider.core.common.base.RqsBody;
import com.spider.core.common.constant.Constant;
import com.spider.core.common.constant.Constant.RspEnum;
import com.spider.core.common.i18n.MessageSourceService;
import com.spider.core.common.spring.SpringUtil;
import com.spider.core.common.utils.RequestUtil;
import com.spider.ma.common.constant.BasicConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 网关入口请求过滤器
 * <p>1、验证sign</p>
 * <p>3、根据json method进行url地址修改</p>
 *
 * @author Spiderman
 * @version $Id: GatewayFilter,v 0.1 2021/07/28 11:47 Exp $$
 */
@WebFilter(urlPatterns = "/gateway", filterName = "gateway")
public class GatewayFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(GatewayFilter.class);
    private static MessageSourceService messageSourceService = SpringUtil.getBean("messageSourceService", MessageSourceService.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("================网关过滤器初始化=====================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contentType = request.getContentType();
        RqsBody rqsBody;
        if (contentType.contains(Constant.CONTENT_TYPE_MULTIPART)) {
            rqsBody = RequestUtil.getParamData(request);
        } else {
            rqsBody = RequestUtil.getInputJSON(request);
        }
        boolean isPass = checkHandle(rqsBody, request, response);
        if (isPass) {
            String method = rqsBody.getMethod();
            request.setAttribute(BasicConstants.RQS_BODY, rqsBody);
            //服务器端重定向到对应controller
            request.getRequestDispatcher(method).forward(request, response);
        }
    }

    /**
     * 请求检查处理
     *
     * @param rqsBody
     * @param request
     * @param response
     */
    private boolean checkHandle(RqsBody rqsBody, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (null == rqsBody) {
            logger.warn("请求参数为空");
            requestInfo(RspEnum.PARAM_INCORRECT, request, response);
            return false;
        }
        if (rqsBody.checkFieldIsNull()) {
            logger.error("参数不正确：" + rqsBody);
            requestInfo(RspEnum.PARAM_INCORRECT, request, response);
            return false;
        }
        return true;
    }


    /**
     * 异常统一跳转
     *
     * @param rspEnum
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void requestInfo(RspEnum rspEnum, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("code", rspEnum.key);
        request.setAttribute("msg", getMessageDetail(rspEnum.value));
        request.getRequestDispatcher("error").forward(request, response);
    }

    /**
     * 获取国际化错误详细信息
     *
     * @param msg 异常信息
     * @return 错误描述类型
     */
    private String getMessageDetail(String msg) {
        String messageSource = messageSourceService.getMessage(msg);
        if (StringUtils.isEmpty(messageSource)) {
            messageSource = msg;
        }
        return messageSource;
    }

    @Override
    public void destroy() {

    }
}
