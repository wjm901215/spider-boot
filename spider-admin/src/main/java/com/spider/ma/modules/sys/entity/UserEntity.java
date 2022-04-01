package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 系统用户表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-24 11:29:36
 */
@TableName("sys_user")
@Data
public class UserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 人员ID
     */
    private String personnelId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态  0：停用   1：启用
     */
    private Integer status;
    /**
     * 用户功能集合
     */
    @TableField(exist = false)
    private List<String> roleIdList;
    /**
     * 数据权限集合
     */
    @TableField(exist = false)
    private List<UserDataEntity> userDataEntity;
    /**
     * 登陆账号
     */
    @TableField(exist = false)
    private String account;
    /**
     * 部门ID
     */
    @TableField(exist = false)
    private String deptId;
    /**
     * 语言
     */
    @TableField(exist = false)
    private String lang;
    /**
     * 工作类型
     */
    @TableField(exist = false)
    private Integer jobType;
    /**
     * 工作状态
     */
    @TableField(exist = false)
    private Integer workingStatus;
    /**
     * 部门名字
     */
    @TableField(exist = false)
    private String deptName;
    /**
     * 名字
     */
    @TableField(exist = false)
    private String name;
    /**
     * 租户数据权限
     */
    @TableField(exist = false)
    private List<String> tenantIds;
    /**
     * 员工性别
     */
    @TableField(exist = false)
    private String sex;
    /**
     * 手机号码
     */
    @TableField(exist = false)
    private String mobile;
    /**
     * 居住地址
     */
    @TableField(exist = false)
    private String address;
    /**
     * 身份证号码
     */
    @TableField(exist = false)
    private String identityNo;
    /**
     * 生日
     */
    @TableField(exist = false)
    private LocalDate birthDate;
    /**
     * 入职日期
     */
    @TableField(exist = false)
    private LocalDate entryDate;
    /**
     * 用户头像
     */
    @TableField(exist = false)
    private String headUrl;
    /**
     * 用户角色
     */
    @TableField(exist = false)
    private String roleName;
}
