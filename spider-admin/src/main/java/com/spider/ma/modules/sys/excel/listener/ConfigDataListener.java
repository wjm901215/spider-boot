package com.spider.ma.modules.sys.excel.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.google.common.eventbus.EventBus;
import com.spider.core.common.utils.FastJsonUtil;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.entity.ConfigEntity;
import com.spider.ma.modules.sys.excel.event.ExcelProcessEvent;
import com.spider.ma.modules.sys.excel.handler.ExcelEventListener;
import com.spider.ma.modules.sys.excel.impt.ConfigData;
import com.spider.ma.modules.sys.excel.rule.ImportRule;
import com.spider.ma.modules.sys.service.ConfigService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置文件模版读取类
 * <p>有个很重要的点 ConfigDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去</p>
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.excel.listener.ConfigDataListener,v 0.1 2021/7/9 22:08 Exp $$
 */
@Slf4j
public class ConfigDataListener extends AnalysisEventListener<ConfigData> {
    /**
     * 配置文件Service
     */
    private final ConfigService configService;

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;

    List<ConfigEntity> list = new ArrayList<>();

    List<ConfigData> listNotValid = new ArrayList<>();

    private EventBus eventBus;

    private ImportRule rule;

    public ConfigDataListener(ConfigService configService, EventBus eventBus, ExcelEventListener listener, ImportRule rule) {
        this.configService = configService;
        this.eventBus = eventBus;
        this.rule = rule;
        //事件注册
        this.eventBus.register(listener);
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ConfigData data, AnalysisContext context) {
        log.info("解析到一条数据:{}", FastJsonUtil.toJSONString(data));
        if (rule.validateMustImport(data,configService)) {
            ConfigEntity configEntity = new ConfigEntity();
            BeanUtil.copyProperties(data, configEntity);
            configEntity.setTenantId(ShiroUtils.getUserEntity().getTenantId());
            configEntity.setStatus(BaseConstants.Status.ENABLE.key);
            configEntity.setIsCommon(BaseConstants.YesOrNoEnum.N.value);
            configEntity.setCreateBy(ShiroUtils.getUserId());
            configEntity.setCreateTime(LocalDateTime.now());
            list.add(configEntity);
            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
            if (list.size() >= BATCH_COUNT) {
                configService.insertConfigDataBatch(list);
                //发送事件到ExcelHandler
                postEvent(list.size(), true);
                // 存储完成清理 list
                list.clear();
            }
        } else {
            //没有通过规则校验的数据,放入了listNotValid,并且不予以导入
            listNotValid.add(data);
        }
    }

    /**
     * 事件发送
     * @param size 成功处理数
     * @param allOk
     */
    private void postEvent(Integer size, Boolean allOk) {
        ExcelProcessEvent processEvent = new ExcelProcessEvent<ConfigData>();
        processEvent.setListNotValid(this.listNotValid);
        processEvent.setAllOk(allOk);
        processEvent.setSuccessCount(size);
        eventBus.post(processEvent);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if(list.size()>0) {
            configService.insertConfigDataBatch(list);
        }
        log.info("所有数据解析完成！");
        //发送事件到ExcelHandler
        postEvent(list.size(), true);
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            String errorMsg=String.format("数据解析失败第%1$s行，第%1$s列解析异常",excelDataConvertException.getRowIndex()+1,excelDataConvertException.getColumnIndex()+1);
            log.error(errorMsg);
            ConfigData configData = new ConfigData();
            configData.setErrorMsg(errorMsg);
            listNotValid.add(configData);
        }
    }
}
