package com.spider.ma.modules.sys.enumerate;

/**
 * 系统管理常量类
 *
 * @author Spiderman
 * @version $Id: HomeConstants,v 0.1 2021/12/13 11:24 Exp $$
 */
public class SysConstants {
    /**
     * 岗位类型
     */
    public enum JobType {
        MANAGER(11, "管理员"), USER(12, "用户");

        public int key;
        public String value;

        JobType(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 通用返回码
     */
    public enum MenuResponseEnum {
        MENU_NOT_FOUND(2001, "当前用户无菜单，无法登录"),
        USER_NOT_FOUND_PASSWORD_ERROR(2002, "账号不存在、密码错误"),
        USER_LOCKING(2003, "当前账号被锁定，无法登录"),
        MENU_URL_EMPTY(2004, "菜单URL不能为空"),
        MENU_DIRECTORY_TYPE(2005, "上级菜单只能为目录类型"),
        MENU_MENU_TYPE(2006, "上级菜单只能为菜单类型"),
        MENU_SYS_MENU_NOT_DELETE(2007, "系统菜单，不能删除"),
        MENU_SUB_MENU_DELETE(2008, "请先删除子菜单或按钮");
        public int key;
        public String value;

        MenuResponseEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 通用返回码
     */
    public enum LoginResponseEnum {
        USER_NOT_FOUND_PASSWORD_ERROR(1004, "账号或密码不正确"),
        USER_LOCKING(1005, "账号已被锁定,请联系管理员");
        public int key;
        public String value;

        LoginResponseEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 通用返回码
     */
    public enum UserResponseEnum {
        USER_EXIST_ERROR(1006, "用户已存在"),
        PASSWORD_IS_INCORRECT(1007, "原密码不正确");
        public int key;
        public String value;

        UserResponseEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
