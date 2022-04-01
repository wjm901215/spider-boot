package com.spider.ma.modules.sys.excel.handler;

import com.google.common.eventbus.Subscribe;
import com.spider.ma.modules.sys.excel.event.ExcelProcessEvent;
import com.spider.ma.modules.sys.excel.impt.ConfigData;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件监听器，可以监听多个事件。处理方法添加 @Subscribe 注解即可
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.excel.handler.ExcelEventListener,v 0.1 2021/7/20 10:51 Exp $$
 */
@Component
public class ExcelEventListener {
    /**
     * 导入成功数
     */
    private int successCount;

    private boolean isAllOk;
    /**
     * 导入失败列表
     */
    private List<T> listNotValid = null;

    /**
     * @return
     */
    public ExcelProcessEvent getProcessEventInListener() {
        ExcelProcessEvent processEvent = new ExcelProcessEvent<ConfigData>();
        processEvent.setListNotValid(this.listNotValid);
        processEvent.setAllOk(this.isAllOk);
        processEvent.setSuccessCount(this.successCount);
        return processEvent;
    }

    /**
     * 通过在ConfigDataListener中的事件event post过来数据
     *
     * @param event
     */
    @Subscribe
    public void excelProcessEvent(ExcelProcessEvent event) {
        if (event.getListNotValid() != null) {
            this.listNotValid = new ArrayList<>();
        }
        //每次将非法的excel条目传入
        this.listNotValid = event.getListNotValid();
        successCount += event.getSuccessCount();
        isAllOk = event.getAllOk();
    }

    public void resetEventHandled() {
        successCount = 0;
        isAllOk = false;
        listNotValid = null;
    }
}
