package com.spider.ma.common.ascept;

import com.spider.ma.common.utils.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 公共属性值设置切面
 *
 * @author pengle
 * @email pengL@gmail.com
 * @date 2019-07-30 14:00
 */
@Aspect
@Configuration
public class CommonPropAspect {

    /**
     * 匹配com.spider.ma.modules包下所有以Dao为后缀得类，匹配insert
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.spider.ma.modules..*.*Dao.insert*(..)) || execution(* com.baomidou.mybatisplus.service.IService.insert*(..))")
    public Object insertAround(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try{
            //设置公共属性
            setProperty(point,false);
        }catch (Exception e){
            //没有找到方法得异常，暂不处理
        }
        //执行方法
        result = point.proceed();
        return result;
    }

    /**
     * 匹配com.spider.ma.modules包下所有以Dao为后缀得类，匹配update
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.spider.ma.modules..*.*Dao.update*(..))|| execution(* com.baomidou.mybatisplus.service.IService.update*(..))")
    public Object updateAround(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try{
            //设置公共属性
            setProperty(point,true);
        }catch (Exception e){
            //没有找到方法得异常，暂不处理
            e.printStackTrace();
        }
        //执行方法
        result = point.proceed();
        return result;
    }

    private void setProperty(ProceedingJoinPoint point,boolean isUpdate)throws Exception{
        Object[] args = point.getArgs();
        //insert方法里面参数大于0，取第一个参数为要设置公共属性得类
        if(args!=null&&args.length>0){
            Object object=args[0];
            //反射设置值
            if(!isUpdate){//如果是insert
                //如果该属性已经没有值，则设置值
                if(isReturnEmpty("getCreateBy",object)){
                    Method setCreateBy = object.getClass().getMethod("setCreateBy", String.class);
                    setCreateBy.invoke(object,ShiroUtils.getUserId());
                }
                if(isReturnEmpty("getCreateTime",object)) {
                    Method setCreateTime = object.getClass().getMethod("setCreateTime", LocalDateTime.class);
                    setCreateTime.invoke(object,LocalDateTime.now());
                }
                if(isReturnEmpty("getTenantId",object)){
                    Method setCreateBy = object.getClass().getMethod("setTenantId", String.class);
                    setCreateBy.invoke(object,ShiroUtils.getUserEntity().getTenantId());
                }
            }else{//如果是update
                //如果该属性已经没有值，则设置值
                if(isReturnEmpty("getUpdateBy",object)){
                    Method setUpdateBy = object.getClass().getMethod("setUpdateBy", String.class);
                    setUpdateBy.invoke(object,ShiroUtils.getUserId());
                }
                //如果该属性已经没有值，则设置值
                if(isReturnEmpty("getUpdateTime",object)){
                    Method setUpdateTime = object.getClass().getMethod("setUpdateTime", LocalDateTime.class);
                    setUpdateTime.invoke(object,LocalDateTime.now());
                }
            }
        }
    }

    private boolean isReturnEmpty(String methodName,Object object){
        try {
            Method getCreateBy = object.getClass().getMethod(methodName);
            Object returnObj=getCreateBy.invoke(object);
            if(returnObj==null||"".equals(returnObj)){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
}

