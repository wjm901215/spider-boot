package com.spider.ma.config;

import com.spider.ma.common.annotation.DataAssessAop;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Method;

/**
 * 自定义权限工具类
 *
 * @author Spiderman
 * @version $Id: com.spider.ma.common.mybatis.PermissionUtils,v 0.1 2021/7/24 15:30 Exp $$
 */
public class PermissionConfig {

    public static DataAssessAop getPermissionByDelegate(MappedStatement mappedStatement) {
        DataAssessAop dataAssessAop = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
            final Class cls = Class.forName(className);
            final Method[] method = cls.getMethods();
            for (Method me : method) {
                if (me.getName().equals(methodName) && me.isAnnotationPresent(DataAssessAop.class)) {
                    dataAssessAop = me.getAnnotation(DataAssessAop.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataAssessAop;
    }
}
