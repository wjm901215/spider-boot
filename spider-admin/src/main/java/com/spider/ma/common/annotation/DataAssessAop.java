package com.spider.ma.common.annotation;

import java.lang.annotation.*;


/**
 * 数据权限过滤
 * <p>如需使用数据权限过滤，则在DAO具体方法中增加该注解@DataAssessAop</p>
 * @author Spiderman
 * @version $Id: com.spider.ma.common.mybatis.PermissionUtils,v 0.1 2021/7/24 15:30 Exp $$
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface DataAssessAop {
    String value() default "";
    /**
     * 数据权限租户列字段
     */
    String tenantId() default "TENANT_ID";

    /**
     * 排序
     * @return
     */
    String orderBy() default "";
}
