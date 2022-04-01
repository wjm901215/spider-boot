package com.spider.ma.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体bean父类
 * 
 * @author wangjun
 * @date 2014-08-13
 */
@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态枚举
	 *
	 */
	public static enum STATE {
		ENABLE(0, "可用"), DISABLE(1, "禁用");
		public int key;
		public String value;

		private STATE(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static STATE get(int key) {
			STATE[] values = STATE.values();
			for (STATE object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 删除枚举
	 *
	 */
	public static enum DELETED {
		NO(0, "未删除"), YES(1, "已删除");
		public int key;
		public String value;

		private DELETED(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static DELETED get(int key) {
			DELETED[] values = DELETED.values();
			for (DELETED object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 停车场重置枚举
	 *
	 */
	public static enum RESET {
		NO(0, "不重置"), YES(1, "重置");
		public int key;
		public String value;

		private RESET(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public static DELETED get(int key) {
			DELETED[] values = DELETED.values();
			for (DELETED object : values) {
				if (object.key == key) {
					return object;
				}
			}
			return null;
		}
	}

	/**
	 * 是否删除0:正常;1:删除
	 */
	private Integer deleted;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新人
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}
