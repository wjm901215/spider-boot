package com.spider.ma.common.enumerate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SpiderMan
 * @created 2021/10/11
 */
public class BaseConstants {

    /**
     * 语言线程变量key
     */
    public static final String LANG = "LANG";
    public static final Object BASE_PWD = "123456";
    public static final String MENU_PARENT_ID="0";
    /**
     * 超级管理员ID
     */
    public static final String SUPER_ADMIN = "supadmin";

    /**
     * 分页查询默认页
     */
    public static final String DEFAULT_PAGE = "1";

    /**
     * 导出分页最大条数
     */
    public static final String EXPORT_PAGE_SIZE = "99999999";
    /**
     * 报表类型
     */
    public enum ReportType {
        date, month, year
    }

    /**
     * 人员离职状态
     */
    public enum PepStatus {
        LEAVE_JOB(0, "离职"), ENTER_JOB(1, "在职");
        public int key;
        public String value;

        PepStatus(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 参数类型枚举
     */
    public enum ParamTypeEnum {
        APP(1, "APP"), BACK_STAGE(2, "后台");
        public int key;
        public String value;

        ParamTypeEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 模块类型
     */
    public enum ModuleType {
        PERSONNEL(1, "personnel"),
        OTHER(999, "others");

        public int key;
        public String value;

        ModuleType(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public static String getValue(Integer key) {
            if (key == null) {
                return OTHER.value;
            }
            ModuleType[] types = ModuleType.values();
            for (int i = 0; i < types.length; i++) {
                if (types[i].key == key) {
                    return types[i].value;
                }
            }
            return OTHER.value;
        }
    }

    /**
     * sys_user_data ext type
     */
    public enum DataPermissionExtType {
        TENANT(10, "租户");

        public int key;
        public String value;

        DataPermissionExtType(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 新增修改用户返回错误信息
     */
    public enum SaveUserErrorCode {
        NO_PERMISSION(1001, "不允许的操作");

        public int key;
        public String value;

        SaveUserErrorCode(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * YES OR NO
     */
    public enum YesOrNoEnum {
        Y(1), N(0);

        public int value;

        YesOrNoEnum(int value) {
            this.value = value;
        }
    }
    /**
     * IsDeleted
     */
    public enum Deleted {
        F(0), T(1);

        public int value;

        Deleted(int value) {
            this.value = value;
        }
    }

    /**
     * 人员状态
     */
    public enum Status {
        DISABLE(0, "停用"), ENABLE(1, "启用");

        public int key;
        public String value;

        Status(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 是否在职
     */
    public enum IsIncumbent {
        RESIGNATION(0, "离职"), INCUMBENT(1, "在职");

        public int key;
        public String value;

        IsIncumbent(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 翻译表外部类型
     */
    public enum ExtType {
        MENU_NAME(1), DICTIONARY(2);

        public int value;

        ExtType(int value) {
            this.value = value;
        }

    }

    /**
     * 人员类型10 后台人员：11管理员、12用户；20 业务人员：21监管员、22保洁员
     */
    public enum JobType {
        ADMIN(11, "管理员"),
        USER(12, "用户"),
        SUPERVISOR(21, "监管员"),
        CLEANER(22, "保洁员");
        public int key;
        public String value;

        JobType(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public static List<Integer> getSystemPep() {
            List<Integer> peps = new ArrayList<>();
            peps.add(ADMIN.key);
            peps.add(USER.key);
            return peps;
        }

        public static List<Integer> getServicePep() {
            List<Integer> peps = new ArrayList<>();
            peps.add(SUPERVISOR.key);
            peps.add(CLEANER.key);
            return peps;
        }
    }

    /**
     * 菜单类型
     *
     * @author SpiderMan
     * @email SpiderMan@gmail.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author SpiderMan
     * @email SpiderMan@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum TreeControls {
        TREE("title"), CASCADER("label");
        private String value;

        TreeControls(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 区域级别
     */
    public enum AreaLevelEnum {
        /**
         * 虚拟节点
         */
        REGION_LEVEL_PROVINCE(1),
        /**
         * 市
         */
        REGION_LEVEL_CITY(2),
        /**
         * 区
         */
        REGION_LEVEL_AREA(3);
        private int value;

        AreaLevelEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }



}
