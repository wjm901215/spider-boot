package com.spider.ma.common.enumerate;

/**
 * 全局通用响应码
 *
 * @author Spiderman
 * @version $Id: com.spider.ma.common.enumerate.ResponseConstants,v 0.1 2021/12/4 18:03 Exp $$
 */
public class ResponseConstants {

    /**
     * 导入导出返回码
     */
    public enum ExcelResponseEnum {
        USER_EXIST_ERROR(3001, "导入失败，请检查Excel文件内容");
        public int key;
        public String value;

        ExcelResponseEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }


}
