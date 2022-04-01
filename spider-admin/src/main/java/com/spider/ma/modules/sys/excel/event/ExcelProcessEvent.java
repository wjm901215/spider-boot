package com.spider.ma.modules.sys.excel.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Excel事件对象
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.excel.event.ExcelProcessEvent,v 0.1 2021/7/20 10:47 Exp $$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelProcessEvent<T> {
    /**
     * 导入成功总数
     */
    private Integer successCount;

    private Boolean allOk;
    /**
     * 导入失败列表
     */
    private List<T> listNotValid;
}
