package com.spider.ma.common.utils;


public interface SysConst {

	/**
	 * 系统路径
	 * */
	public final static String CLASS_BASE_PATH = "com.innotek.system.schedule.";

	/** 系统分隔符 */
	public final static String LINE_SEPARATOR = System.getProperty("line.separator");
	/** 系统 路径符 */
	public final static String PATH_SEPARATOR = System.getProperty("path.separator");

	public final static int CACHE_FEE_TYPE_PREPAY = 1;
	public final static int CACHE_FEE_TYPE_REPAY = 2;
	public final static int CACHE_FEE_TYPE_PAY = 3;
	/**
	 * 日志参数
	 */
	public final static String LOG_ARGUMENTS = "log_arguments";

	public final static String SESSION_ATTR_CITYCODE = "cityCode";
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS. ";

	/**
	 * 菜单级别
	 */
	public final static String moduleLevelTop = "0";
	public final static String moduleLevelOne = "1";
	public final static String moduleLevelTwo = "2";

	/**
	 * 是否通用
	 */
	public final static Integer ISNOTCOMMON = 0;
	public final static Integer ISCOMMON = 1;
	/**
	 * 区域级别
	 */
	public final static Integer REGION_LEVEL_VIRSUAL = 0;// 虚拟节点
	public final static Integer REGION_LEVEL_CITY = 1;// 市
	public final static Integer REGION_LEVEL_AREA = 2;// 区
	public final static Integer REGION_LEVEL_STREET = 3;// 街道

	/**
	 * 编码类型
	 * */
	public final static String CODE_TYPE_CITY_CODE = "CITY_CODE";
	public final static String CODE_TYPE_AREA_CODE = "AREA_CODE";
	public final static String CODE_TYPE_STREET_CODE = "STREET_CODE";
	public final static String CODE_TYPE_PARK_CODE = "PARK_CODE";
	public final static String CODE_TYPE_COLL_GROUP_CODE = "COLL_GROUP_CODE";
	public final static String CODE_TYPE_COLL_CODE = "COLL_CODE";
	public final static String CODE_TYPE_DEVICE_CODE = "DEVICE_CODE";
	public final static String CODE_TYPE_CARTAG_CODE = "CAR_TAG";
	public final static String CODE_TYPE_RATE_RULE_CODE = "RATE_RULE_CODE";
	public final static String CODE_TYPE_CUSTOMER_CODE = "CUSTOMER_CODE";
	public final static String CODE_TYPE_VALUE_CITY = "0";

	/**
	 * 虚拟系统模块ID，总机构
	 * */
	public final static Long TOP_MODULE_ID = (long) 0;
	/**
	 * 虚拟机构ID，一级部门parentID
	 */
	public final static Long TOP_DEPARTMENT_ID = (long) 1;

	/**
	 * 管理级和菜单级的moduleUrl
	 * */
	public final static String MANUE_URL = "#";
	/** 系统默认的年份 */
	public static final String DEFAULT_YEAR = "2000-01-01 ";
	public static final String DEFAULT_TIME_TAIL = ":00";

	public static final String[][] DEFAULT_CODE_DATA = new String[][] {
			// 区域编码
			new String[] { "0", CODE_TYPE_AREA_CODE, "", "0", "3" },
			// 街道编码
			new String[] { "0", CODE_TYPE_STREET_CODE, "", "0", "3" },
			// 停车点编码
			new String[] { "0", CODE_TYPE_PARK_CODE, "P", "0", "4" },
			// 收费员组编码
			new String[] { "0", CODE_TYPE_COLL_GROUP_CODE, "G", "0", "4" },
			// 角色编码
			new String[] { "0", CODE_TYPE_COLL_CODE, "C", "0", "4" },
			// 设备编码
			new String[] { "0", CODE_TYPE_DEVICE_CODE, "D", "0", "4" },
			// 计费规则编码
			new String[] { "0", CODE_TYPE_RATE_RULE_CODE, "B", "0", "4" },
			// 车主编码 driver
			new String[] { "0", CODE_TYPE_CUSTOMER_CODE, "DR", "0", "4" },
			//车载标签编码
			new String[] { "0", CODE_TYPE_CARTAG_CODE, "CT", "0", "3" } };

	public static final int CACHE_OPT_ADD = 1;
	public static final int CACHE_OPT_DEL = 2;
	public static final int CACHE_OPT_MOD = 3;

	public static final String ADJUST_JOB_NAME = "FlowAdjustJob";
	//	customerCacheService
	//	memcacheOperateService
	public static final String CACHE_SERVER_CUTOMER = "customerCacheService";
	public static final String CACHE_SERVER_MENCHACHED = "memcacheOperateService";
}
