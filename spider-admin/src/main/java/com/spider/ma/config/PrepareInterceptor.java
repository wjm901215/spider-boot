package com.spider.ma.config;


import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.spider.ma.common.annotation.DataAssessAop;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.core.common.utils.ReflectUtil;
import com.spider.ma.modules.sys.entity.UserDataEntity;
import com.spider.ma.modules.sys.entity.UserEntity;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * mybatis数据权限拦截器 - prepare
 *
 * @author Spiderman
 * @version $Id: com.spider.ma.common.mybatis.PermissionUtils,v 0.1 2021/7/24 15:42 Exp $$
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PrepareInterceptor implements Interceptor {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("进入 PrepareInterceptor 数据权限拦截器...");
        Object target = invocation.getTarget();
        Object statementHandler = this.realTarget(target);
        if (statementHandler instanceof RoutingStatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) statementHandler;
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            DataAssessAop dataAssessAop = PermissionConfig.getPermissionByDelegate(mappedStatement);
            //超级管理员直接放行
            if (dataAssessAop == null || ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN)) {
                log.debug("数据权限放行...");
                return invocation.proceed();
            }
            log.debug("数据权限处理【拼接SQL】...");
            BoundSql boundSql = delegate.getBoundSql();
            String originalSql = boundSql.getSql();
            log.debug("原始SQL：" + originalSql);
            String permissionSql = permissionSql(originalSql, dataAssessAop);
            permissionSql=permissionOrderSql(permissionSql, dataAssessAop);
            log.debug("修改后SQL：" + permissionSql);
            ReflectUtil.setFieldValue(boundSql, "sql", permissionSql);
        }
        return invocation.proceed();
    }

    /**
     * <p>获得真正的处理对象,可能多层代理./p>
     */
    private Object realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return target;
    }

    /**
     * 权限sql包装
     *
     * @param sql
     * @param dataAssessAop
     * @return
     */
    protected String permissionSql(String sql, DataAssessAop dataAssessAop) {
        StringBuilder sbSql = new StringBuilder(sql);
        UserEntity userEntity = ShiroUtils.getUserEntity();
        List<UserDataEntity> userDataList = userEntity.getUserDataEntity();
        sbSql.insert(0, "select * from (");
        //普通账户未分配数据权限，直接给不成立条件
        if (CollectionUtils.isEmpty(userDataList)) {
            sbSql.append(") tt where 1<>1 ");
            return sbSql.toString();
        }
        StringBuilder wrapperTenant = new StringBuilder();
        for (UserDataEntity userDataEntity : userDataList) {
            wrapperTenant.append("'").append(userDataEntity.getExtId()).append("'").append(",");
        }
        sbSql.append(") tt where ").append("(");
        String tenantId = dataAssessAop.tenantId();
        wrapperTenant.delete(wrapperTenant.lastIndexOf(","), wrapperTenant.length());
        wrapperTenant.insert(0, tenantId + " in (").append(")");
        sbSql.append(wrapperTenant.toString());
        sbSql.append(")");
        return sbSql.toString();
    }

    /**
     * 增加排序
     * @param permissionSql
     * @param dataAssessAop
     * @return
     */
    private String permissionOrderSql(String permissionSql, DataAssessAop dataAssessAop) {
        String orderSql=permissionSql;
        String orderBy=dataAssessAop.orderBy();
        if(StringUtils.isNotEmpty(orderBy)){
            orderSql+=" order by "+orderBy;
        }
        return orderSql;
    }


}
