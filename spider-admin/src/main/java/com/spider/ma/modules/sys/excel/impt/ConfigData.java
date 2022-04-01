package com.spider.ma.modules.sys.excel.impt;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 参数管理导入类
 * <p>ExcelProperty 需要和导出模版保持一致</p>
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.excel.impt.ConfigData,v 0.1 2021/7/9 22:03 Exp $$
 */
@Data
public class ConfigData {
    /**
     * KEY
     */
    @ExcelProperty("参数名称")
    private String paramKey;
    /**
     * 配置类型(1APP,2后台系统)
     */
    @ExcelProperty("类型")
    private Integer type;
    /**
     * VALUE
     */
    @ExcelProperty("参数值")
    private String paramValue;
    /**
     * 备注
     */
    @ExcelProperty("备注信息")
    private String remark;
    /**
     * 导入错误信息
     */
    private String errorMsg;
}
