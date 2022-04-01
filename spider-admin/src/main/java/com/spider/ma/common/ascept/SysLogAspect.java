package com.spider.ma.common.ascept;

import cn.hutool.core.util.ObjectUtil;
import com.spider.core.common.utils.FastJsonUtil;
import com.spider.ma.common.annotation.SysLog;
import com.spider.ma.common.utils.HttpContextUtils;
import com.spider.ma.common.utils.IPUtils;
import com.spider.ma.modules.sys.entity.LogEntity;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.ma.modules.sys.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * 系统日志，切面处理类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017年3月8日 上午11:07:35
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private LogService sysLogService;

    @Pointcut("@annotation(com.spider.ma.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        LogEntity sysLog = new LogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = FastJsonUtil.toJSONString(args[0]);
            sysLog.setParam(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if(ObjectUtil.isNotNull(userEntity)){
            sysLog.setUsername(userEntity.getUsername());
            sysLog.setTenantId(userEntity.getTenantId());
        }else{
            Map<String, Object> paramMap = FastJsonUtil.json2Map(sysLog.getParam());
            sysLog.setUsername(ObjectUtil.isNotNull(paramMap)?(String) paramMap.get("username"):"");
        }
        sysLog.setCreateDate(LocalDateTime.now());
        sysLog.setTime(time);
        //保存系统日志
        sysLogService.insert(sysLog);
    }
}
