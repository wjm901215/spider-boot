package com.spider.ma.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.spider.ma.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 人员信息表
 * 
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-22 10:01:31
 */
@TableName("base_personnel")
@Data
public class PersonnelEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public PersonnelEntity(String id) {
		this.id = id;
	}

	public PersonnelEntity() {
	}

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
	 * 所在部门关联ID
	 */
	private String deptId;
	/**
	 * 用户类型（10 后台人员：11管理员、12用户；20 收费人员：21监管员、22保洁员）
	 */
	private Integer jobType;
	/**
	 * 在职状态（0离职、1在职）
	 */
	private Integer status;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 语言
	 */
	private String lang;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 家庭住址
	 */
	private String address;
	/**
	 * 身份证号码
	 */
	private String identityNo;
	/**
	 * 出生日期
	 */
	private LocalDate birthDate;
	/**
	 * 头像地址
	 */
	private String headUrl;
	/**
	 * 入职日期
	 */
	private LocalDate entryDate;
	/**
	 * 离职日期
	 */
	private LocalDate leaveDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 用户名
	 */
	@TableField(exist = false)
	private String username;
	/**
	 * 密码
	 */
	@TableField(exist = false)
	private String password;
	/**
	 * 邮箱
	 */
	@TableField(exist = false)
	private String email;
}
