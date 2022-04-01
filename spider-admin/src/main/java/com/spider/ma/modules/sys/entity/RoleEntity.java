package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-24 11:54:16
 */
@TableName("sys_role")
@Data
public class RoleEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private String roleId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 权限集合
     */
    @TableField(exist = false)
    private List<String> menuIdList;
    /**
     * 半选状态ID
     */
    @TableField(exist = false)
    private List<String> halfMenuIdList;
}
