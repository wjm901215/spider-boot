package com.spider.ma.modules.sys.excel.expt;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用户数据导出模版
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.excel.expt.UserDataTemplate,v 0.1 2021/7/7 18:39 Exp $$
 */
@Data
public class UserDataTemplate {
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String personnelName;
    /**
     * 性别
     */
    @ExcelProperty("性别")
    private String sex;
    /**
     * 手机号码
     */
    @ExcelProperty("手机号码")
    private String mobile;
    /**
     * 人员账号
     */
    @ExcelProperty("人员账号")
    private String account;
    /**
     * 所在部门
     */
    @ExcelProperty("所在部门")
    private String deptName;
    /**
     * 在职
     */
    @ExcelProperty("在职")
    private String workingStatusName;
    /**
     * 账号状态
     */
    @ExcelProperty("账号状态")
    private String statusName;


}
