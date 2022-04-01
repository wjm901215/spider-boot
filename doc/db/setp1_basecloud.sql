/*
 Navicat Premium Data Transfer

 Source Server         : local_82
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 192.168.1.82:3306
 Source Schema         : spider

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 23/09/2019 11:28:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_app_key
-- ----------------------------
DROP TABLE IF EXISTS `base_app_key`;
CREATE TABLE `base_app_key` (
  `ID` char(32) NOT NULL COMMENT '主键ID',
  `APP_ID` char(32) NOT NULL COMMENT '应用ID',
  `TENANT_ID` char(32) NOT NULL COMMENT '租户ID',
  `APP_KEY` varchar(64) NOT NULL COMMENT '应用密钥值',
  `APP_NAME` varchar(32) NOT NULL COMMENT '应用名称',
  `APP_STATUS` varchar(1) NOT NULL COMMENT '应用状态(1.正常0.停用)',
  `APP_DESC` varchar(255) NOT NULL COMMENT '应用描述',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `CREATED_BY` varchar(36) NOT NULL COMMENT '创建人',
  `UPDATED_BY` varchar(36) NOT NULL COMMENT '修改人',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,默认SYSDATE',
  `UPDATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间,默认SYSDATE',
  PRIMARY KEY (`ID`) USING BTREE,
  KEY `APP_KEY_ID_INDEX` (`APP_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用接入密钥表';

-- ----------------------------
-- Records of base_app_key
-- ----------------------------
BEGIN;
INSERT INTO `base_app_key` VALUES ('1', '93f83219ff', '1', 'EGfvomj+eVE=', 'APP', '1', 'APP', 0, '', '', '2021-06-23 15:43:36', '2021-06-23 15:43:36');
COMMIT;

-- ----------------------------
-- Table structure for base_attach_file
-- ----------------------------
DROP TABLE IF EXISTS `base_attach_file`;
CREATE TABLE `base_attach_file` (
  `ID` char(32) NOT NULL COMMENT '主键UUID',
  `FILE_TYPE` tinyint(4) DEFAULT NULL COMMENT '附件类型（1图片，2文件）',
  `MODULE_TYPE` tinyint(4) DEFAULT NULL COMMENT '模块类型（）',
  `RELATION_ID` char(32) DEFAULT NULL COMMENT '关联的业务表ID',
  `FILE_NAME` varchar(32) NOT NULL COMMENT '附件名',
  `FILE_PATH` varchar(200) NOT NULL COMMENT '附件路径',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Table structure for base_personnel
-- ----------------------------
DROP TABLE IF EXISTS `base_personnel`;
CREATE TABLE `base_personnel` (
  `ID` char(32) NOT NULL COMMENT '主键ID',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `DEPT_ID` char(32) DEFAULT NULL COMMENT '所在机构关联ID',
  `COMPANY_ID` char(32) DEFAULT NULL COMMENT '单位id',
  `JOB_TYPE` tinyint(4) DEFAULT NULL COMMENT '用户类型（10 后台人员：11管理员、12用户；20 业务人员：21监管员、22保洁员）',
  `STATUS` tinyint(4) NOT NULL DEFAULT '1' COMMENT '在职状态（0离职、1在职）',
  `NAME` varchar(50) NOT NULL COMMENT '姓名',
  `SEX` varchar(20) NOT NULL COMMENT '性别',
  `MOBILE` varchar(100) DEFAULT NULL COMMENT '手机号',
  `LANG` char(50) DEFAULT NULL COMMENT '语言',
  `ADDRESS` varchar(300) DEFAULT NULL COMMENT '家庭住址',
  `IDENTITY_NO` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `BIRTH_DATE` datetime DEFAULT NULL COMMENT '出生日期',
  `HEAD_URL` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `ENTRY_DATE` datetime NULL DEFAULT NULL COMMENT '入职日期',
  `LEAVE_DATE` datetime NULL DEFAULT NULL COMMENT '离职日期',
  `CREATE_BY` char(32) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `REMARK` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

-- ----------------------------
-- Records of base_personnel
-- ----------------------------
BEGIN;
INSERT INTO `base_personnel` VALUES ('1', '', '1', NULL, 11, 1, '超级管理员', '1', '13257000000', 'zh-CN', '西湖', '110101111010119900', '2021-06-22 15:12:49', NULL, NULL, NULL, NULL, '2021-06-22 15:11:53', NULL, NULL, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for base_tenant
-- ----------------------------
DROP TABLE IF EXISTS `base_tenant`;
CREATE TABLE `base_tenant` (
  `ID` char(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(100) NOT NULL COMMENT '租户名称',
  `CODE` varchar(100) NOT NULL COMMENT '租户编码',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人帐号',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '更新人帐号',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `DELETED` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户表';

-- ----------------------------
-- Records of base_tenant
-- ----------------------------
BEGIN;
INSERT INTO `base_tenant` VALUES ('1', '系统租户', 'T00000', '1', '2021-06-16 17:11:57', 'ab5cd57ecc5746709ed4a5dd76f71481', '2019-07-25 11:39:45', 0);
COMMIT;

-- ----------------------------
-- Table structure for base_translate
-- ----------------------------
DROP TABLE IF EXISTS `base_translate`;
CREATE TABLE `base_translate` (
  `ID` char(32) NOT NULL,
  `LANG` varchar(50) NOT NULL COMMENT '语言',
  `EXT_ID` char(32) NOT NULL COMMENT '外部id',
  `EXT_TYPE` tinyint(6) NOT NULL COMMENT '外部类型(1:目录 2:数据字典)',
  `TRANSLATION` varchar(200) NOT NULL COMMENT '翻译',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='翻译表(sys_menu sys_dictionay)';

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `AK_Key_2` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 作为 Blob 类型存储';

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='以blob类型存储Quartz的Calendar信息';

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储Cron Trigger，包括Cron表达式和时区';

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='触发器与任务关联表';

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务详细信息表';

-- ----------------------------
-- Records of qrtz_job_details

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储锁信息';

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存在已暂停的Trigger信息';

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '配置文件中org.quartz.scheduler.instanceId配置的名字，如果设置为AUTO,quartz会根据物理机名和当前时间产生一个名字',
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL COMMENT '上次检入时间',
  `CHECKIN_INTERVAL` bigint(13) NOT NULL COMMENT '检入间隔时间',
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调度器状态表';

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储简单的Trigger';

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `FK_Reference_1` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='触发器信息表';

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `JOB_ID` char(36) NOT NULL COMMENT '任务ID',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `BEAN_NAME` varchar(200) DEFAULT NULL COMMENT 'SPRING BEAN名称',
  `METHOD_NAME` varchar(100) DEFAULT NULL COMMENT '方法名',
  `PARAMS` varchar(2000) DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` varchar(100) DEFAULT NULL COMMENT 'CRON表达式',
  `IS_COMMON` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否通用（0.非通用；1.通用）',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '任务状态  0：正常  1：暂停',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`JOB_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';
INSERT INTO `schedule_job` VALUES ('4f0fcb837cb54075802c9c22d6c1df27', NULL, 'simpleTask', 'test', NULL, '0 */1 * * * ?', 1, 0, '定时任务示例', '2021-07-06 21:53:13');
-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `LOG_ID` char(36) NOT NULL COMMENT '任务日志ID',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `JOB_ID` char(36) NOT NULL COMMENT '任务ID',
  `BEAN_NAME` varchar(200) DEFAULT NULL COMMENT 'SPRING BEAN名称',
  `METHOD_NAME` varchar(100) DEFAULT NULL COMMENT '方法名',
  `PARAMS` varchar(2000) DEFAULT NULL COMMENT '参数',
  `STATUS` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `TIMES` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`) USING BTREE,
  KEY `JOB_ID` (`JOB_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `ID` char(32) NOT NULL COMMENT 'ID',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `NAME` varchar(50) NOT NULL COMMENT '区域名称',
  `CODE` varchar(20) NOT NULL COMMENT '区域编码',
  `PARENT_ID` char(32) NOT NULL COMMENT '上级区域ID',
  `LEVEL` tinyint(4) NOT NULL COMMENT '区域级别',
  `LONGITUDE_BAIDU` decimal(10,6) DEFAULT NULL COMMENT '经度（百度）',
  `LATITUDE_BAIDU` decimal(10,6) DEFAULT NULL COMMENT '维度（百度）',
  `LONGITUDE_GAODE` decimal(10,6) DEFAULT NULL COMMENT '经度（高德）',
  `LATITUDE_GAODE` decimal(10,6) DEFAULT NULL COMMENT '维度（高德）',
  `ORDER_NUM` decimal(13,10) NOT NULL DEFAULT '0.0000000000' COMMENT '排序号',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:正常;1:删除',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `ID` char(32) NOT NULL,
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `PARAM_KEY` varchar(50) DEFAULT NULL COMMENT 'KEY',
  `PARAM_VALUE` varchar(2000) DEFAULT NULL COMMENT 'VALUE',
  `TYPE` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型（1APP、2后台）',
  `STATUS` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0.停用；1.启用）',
  `IS_COMMON` tinyint(4) NOT NULL COMMENT '是否通用',
  `REMARK` varchar(500) DEFAULT NULL COMMENT '备注',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `AK_Key_2` (`PARAM_KEY`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `spider`.`sys_config`(`ID`, `TENANT_ID`, `PARAM_KEY`, `PARAM_VALUE`, `TYPE`, `STATUS`, `IS_COMMON`, `REMARK`, `CREATE_BY`, `CREATE_TIME`, `UPDATE_BY`, `UPDATE_TIME`, `DELETED`) VALUES ('12', '1', 'ADMIN_CLOUD_STORAGE_CONFIG_KEY', '{\n    \"type\": 4, \n    \"minioDomain\": \"http://127.0.0.1:9000\", \n    \"minioPrefix\": \"miniofile\", \n    \"minioAccessKey\": \"minioadmin\", \n    \"minioSecretKey\": \"minioadmin\", \n    \"minioBucketName\": \"grabage\"\n}', 1, 1, 0, '云存储配置KEY', '1', '2019-07-24 17:42:07', 'supadmin', '2021-07-21 23:03:52', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `ID` char(32) NOT NULL COMMENT '主键ID',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `NAME` varchar(50) NOT NULL COMMENT '部门名称',
  `SHORT_NAME` varchar(50) DEFAULT NULL COMMENT '部门简称',
  `CODE` varchar(50) NOT NULL COMMENT '部门编码',
  `PARENT_ID` char(32) NOT NULL COMMENT '上级部门ID',
  `ORDER_NUM` decimal(13,10) NOT NULL DEFAULT '0.0000000000' COMMENT '排序号',
  `LONGITUDE_BAIDU` decimal(10,6) DEFAULT NULL COMMENT '经度（百度）',
  `LATITUDE_BAIDU` decimal(10,6) DEFAULT NULL COMMENT '维度（百度）',
  `LONGITUDE_GAODE` decimal(10,6) DEFAULT NULL COMMENT '经度（高德）',
  `LATITUDE_GAODE` decimal(10,6) DEFAULT NULL COMMENT '维度（高德）',
  `LEVEL` int(11) DEFAULT NULL COMMENT '机构层级，顶层机构为1，二级机构为2，以此类推',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除0:正常;1:删除',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人帐号',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '更新人帐号',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `REMARK` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES ('2e15535aaed040a58bd6f1ca122a70e6', '1', '管理部门', 'GLBM', 'HZ19030610174016510145', '-1', 1.0000000000, 30.236515, 120.018491, 120.012074, 30.230227, 1, 0, 'ADMIN001', '2019-03-06 10:17:40', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `ID` char(32) NOT NULL COMMENT 'uuid',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `CODE_TYPE` varchar(50) DEFAULT NULL COMMENT '信息类别',
  `CODE_VALUE` varchar(10) DEFAULT NULL COMMENT '信息值',
  `CODE_TEXT` varchar(50) DEFAULT '1' COMMENT '信息描述',
  `CODE_NAME` varchar(50) DEFAULT NULL COMMENT '信息名称',
  `IS_COMMON` tinyint(4) NOT NULL COMMENT '是否通用（0.非通用；1.通用）',
  `STATE` tinyint(4) DEFAULT '1' COMMENT '字典状态（0.停用；1.启用）',
  `ORDER_NUM` decimal(13,10) NOT NULL DEFAULT '0.0000000000' COMMENT '排序号',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `sys_dictionary` VALUES ('0beb2859adab4373a959b68bc2b31ba4', NULL, 'JOB_TYPE', '11', '管理员', '用户类型', 1, 1, 1.0000000000, 'ADMIN001', '2019-07-24 15:23:24', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('359bba7a28dd4b42b89b5d519f91001c', NULL, 'JOB_TYPE', '12', '用户', '用户类型', 1, 1, 2.0000000000, 'ADMIN001', '2019-07-24 15:23:41', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('4348e76a78ca43ccbd4c5dc0443058e6', NULL, 'JOB_STATUS', '1', '在职', '在职状态', 1, 1, 2.0000000000, 'ADMIN001', '2019-07-24 17:07:54', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('54dbf580d2284491bc23553c0039f362', NULL, 'IS_COMMON', '1', '通用', '是否通用', 1, 1, 1.0000000000, 'ADMIN001', '2019-07-22 19:20:09', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('69a2065dd80111e8a0d2309c233ce78b', NULL, 'SENSOR_STATUS', '01', '正常', '设备状态', 1, 1, 1.0000000000, '', '2019-07-26 16:49:46', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('69a8b5ebd80111e8a0d2309c233ce78b', NULL, 'SENSOR_STATUS', '02', '异常', '设备状态', 1, 1, 1.0000000000, '', '2019-07-26 16:49:46', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('a4658447205b4aa6b062f1dfa426604c', NULL, 'IS_COMMON', '0', '不通用', '是否通用', 1, 1, 2.0000000000, 'ADMIN001', '2019-07-22 19:23:09', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('b080f31e2a974b5490d96c95e516421a', NULL, 'JOB_STATUS', '0', '离职', '在职状态', 1, 1, 1.0000000000, 'ADMIN001', '2019-07-24 17:07:31', NULL, NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('995aeb611402412a968fd46dcf66fcba', NULL, 'SEX', '1', '男', '性别', 1, 1, 1.0000000000, 'ADMIN001', '2019-07-24 16:52:32', 'ADMIN001', '2019-07-24 17:24:35', 0);
INSERT INTO `sys_dictionary` VALUES ('d73ca047a9f847408dbeb28504ff090c', NULL, 'SEX', '2', '女', '性别', 1, 1, 2.0000000000, 'ADMIN001', '2019-07-24 16:52:49', 'ADMIN001', '2019-07-24 17:24:43', 0);
INSERT INTO `sys_dictionary` VALUES ('base_dict4', NULL, 'STATUS', '0', '禁用', '状态', 1, 1, 1.0000000000, '1', '2021-06-27 10:23:47', 'd752d33565a8494abba06ba1746b63fe', '2021-06-10 15:17:47', 0);
INSERT INTO `sys_dictionary` VALUES ('base_dict6', NULL, 'STATUS', '1', '启用', '状态', 1, 1, 1.0000000000, '1', '2021-06-27 10:23:47', 'd752d33565a8494abba06ba1746b63fe', '2021-06-10 15:18:01', 0);
INSERT INTO `sys_dictionary` VALUES ('base_dict81', '', 'LANG', 'zh-CN', '中文-简体', '系统语言', 1, 1, 1.0000000000, '1', '2021-06-28 10:25:12', '', NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('base_dict82', '', 'LANG', 'en-US', 'English-US', '系统语言', 1, 1, 1.0000000000, '1', '2021-06-28 10:25:12', '', NULL, 0);
INSERT INTO `sys_dictionary` VALUES ('61a623da8bd04880912fdb9184ff9a26', NULL, 'CONFIG_TYPE', '1', 'APP', '参数类型', 1, 1, 1.0000000000, 'supadmin', '2021-07-05 18:34:28', NULL, NULL, 0);
INSERT INTO `sys_dictionary`VALUES ('dd48c17cafc943c58c31aaa4d476766b', NULL, 'CONFIG_TYPE', '2', '后台', '参数类型', 1, 1, 2.0000000000, 'supadmin', '2021-07-05 18:35:10', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `ID` char(32) NOT NULL,
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `OPERATION` varchar(100) DEFAULT NULL COMMENT '用户操作',
  `METHOD` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `PARAM` text DEFAULT NULL COMMENT '请求参数',
  `TIME` int(11) DEFAULT NULL COMMENT '执行时长(毫秒)',
  `IP` varchar(64) DEFAULT '1' COMMENT 'IP地址',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` char(32) NOT NULL,
  `PARENT_ID` char(32) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `NAME` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `URL` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `PERMS` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `TYPE` tinyint(4) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` decimal(13,10) NOT NULL DEFAULT '0.0000000000' COMMENT '排序号',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表\r\n';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', 'e33003052b7349cdbda04956c1aa1d27', '系统管理', NULL, NULL, 0, 'system', 4.0000000000, '', '2021-06-20 19:45:48', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', NULL, 'sys:schedule:delete', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', NULL, 'sys:user:list,sys:user:info,sys:role:list', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', NULL, 'sys:user:save', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', NULL, 'sys:user:update', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', NULL, 'sys:user:delete', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2', '33d5f1bf1a684c898fffe33c72058f5a', '用户管理', 'sys/user', 'sys:user:list', 1, 'user1', 1.0000000000, '', '2021-06-20 19:45:48', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', NULL, 'sys:role:delete', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', NULL, 'sys:menu:delete', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'parameter', 6.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('29', '539a87a4c6a74758949de838dfb4ec41', '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7.0000000000, '', '2021-06-20 19:45:50', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2ddb10b3adf211e99126309c233ce78b', '1', '租户管理', 'base/tenant', NULL, 1, 'config', 6.0000000000, '1', '2019-07-24 17:07:21', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2de08e61adf211e99126309c233ce78b', '2ddb10b3adf211e99126309c233ce78b', '查看', NULL, 'base:tenant:list,base:tenant:info', 2, NULL, 6.0000000000, '1', '2019-07-24 17:07:22', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2de5c1e6adf211e99126309c233ce78b', '2ddb10b3adf211e99126309c233ce78b', '新增', NULL, 'base:tenant:save', 2, NULL, 6.0000000000, '1', '2019-07-24 17:07:22', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2dea9eafadf211e99126309c233ce78b', '2ddb10b3adf211e99126309c233ce78b', '修改', NULL, 'base:tenant:update', 2, NULL, 6.0000000000, '1', '2019-07-24 17:07:22', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('2dee9e7cadf211e99126309c233ce78b', '2ddb10b3adf211e99126309c233ce78b', '删除', NULL, 'base:tenant:delete', 2, NULL, 6.0000000000, '1', '2019-07-24 17:07:22', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('3', '33d5f1bf1a684c898fffe33c72058f5a', '角色管理', 'sys/role', NULL, 1, 'role', 2.0000000000, '', '2021-06-20 19:45:48', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('33d5f1bf1a684c898fffe33c72058f5a', 'e33003052b7349cdbda04956c1aa1d27', '组织架构', '', '', 0, 'dept', 1.0000000000, '3', '2021-06-12 17:04:34', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', NULL, 1, 'menu', 3.0000000000, '', '2021-06-20 19:45:48', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('4af6e630f92a11e8879800163e1b3039', '1', '区域管理', 'sys/area', NULL, 1, 'area', 6.0000000000, '1', '2021-06-06 15:41:07', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('4af9c5a7f92a11e8879800163e1b3039', '4af6e630f92a11e8879800163e1b3039', '查看', NULL, 'sys:area:list,sys:area:info', 2, NULL, 6.0000000000, '1', '2021-06-06 15:41:07', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('4afb4635f92a11e8879800163e1b3039', '4af6e630f92a11e8879800163e1b3039', '新增', NULL, 'sys:area:save', 2, NULL, 6.0000000000, '1', '2021-06-06 15:41:07', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('5', '539a87a4c6a74758949de838dfb4ec41', 'SQL监控', 'http://127.0.0.1:9099/druid/sql.html', NULL, 1, 'sqlmonitor', 4.0000000000, '', '2021-06-20 19:45:48', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('539a87a4c6a74758949de838dfb4ec41', 'e33003052b7349cdbda04956c1aa1d27', '安全审计', '', '', 0, 'safe', 5.0000000000, 'd752d33565a8494abba06ba1746b63fe', '2021-06-17 17:02:44', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('5b57b2eaf92a11e8879800163e1b3039', '4af6e630f92a11e8879800163e1b3039', '修改', NULL, 'sys:area:update', 2, NULL, 6.0000000000, '1', '2021-06-06 15:41:35', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('5b59082af92a11e8879800163e1b3039', '4af6e630f92a11e8879800163e1b3039', '删除', NULL, 'sys:area:delete', 2, NULL, 6.0000000000, '1', '2021-06-06 15:41:35', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', NULL, 1, 'task', 5.0000000000, '', '2021-06-20 19:45:48', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('604c9bf6f6df11e89b34309c233ce78b', '33d5f1bf1a684c898fffe33c72058f5a', '部门管理', 'sys/dept', NULL, 1, 'mechanism', 6.0000000000, '', '2021-06-03 17:40:00', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('6053ef17-f6df-11e8-9b34-309c233c', '604c9bf6f6df11e89b34309c233ce78b', '查看', NULL, 'sys:dept:list,sys:dept:info', 2, NULL, 6.0000000000, '', '2021-06-03 17:40:00', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('60589e2a-f6df-11e8-9b34-309c233c', '604c9bf6f6df11e89b34309c233ce78b', '新增', NULL, 'sys:dept:save', 2, NULL, 6.0000000000, '', '2021-06-03 17:40:00', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('605e0bf2-f6df-11e8-9b34-309c233c', '604c9bf6f6df11e89b34309c233ce78b', '修改', NULL, 'sys:dept:update', 2, NULL, 6.0000000000, '', '2021-06-03 17:40:00', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('6062caa0-f6df-11e8-9b34-309c233c', '604c9bf6f6df11e89b34309c233ce78b', '删除', NULL, 'sys:dept:delete', 2, NULL, 6.0000000000, '', '2021-06-03 17:40:00', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', NULL, 'sys:schedule:save', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', NULL, 'sys:schedule:update', 2, NULL, 0.0000000000, '', '2021-06-20 19:45:49', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e33003052b7349cdbda04956c1aa1d27', '0', '设置', NULL, NULL, 0, 'setting', 7.0000000000, '2', '2021-06-26 14:40:27', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e8ea0df3f9e211e8879800163e1b3039', '1', '字典管理', 'sys/dictionary', NULL, 1, 'dictionary', 6.0000000000, '1', '2021-06-07 13:42:40', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e8ee8d73f9e211e8879800163e1b3039', 'e8ea0df3f9e211e8879800163e1b3039', '查看', NULL, 'sys:dictionary:list,sys:dictionary:info', 2, NULL, 6.0000000000, '1', '2021-06-07 13:42:40', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e8ef4737f9e211e8879800163e1b3039', 'e8ea0df3f9e211e8879800163e1b3039', '新增', NULL, 'sys:dictionary:save', 2, NULL, 6.0000000000, '1', '2021-06-07 13:42:40', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e8f00b53f9e211e8879800163e1b3039', 'e8ea0df3f9e211e8879800163e1b3039', '修改', NULL, 'sys:dictionary:update', 2, NULL, 6.0000000000, '1', '2021-06-07 13:42:40', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('e8f4747df9e211e8879800163e1b3039', 'e8ea0df3f9e211e8879800163e1b3039', '删除', NULL, 'sys:dictionary:delete', 2, NULL, 6.0000000000, '1', '2021-06-07 13:42:40', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('33831d4710264741a10031dd85d12e87', '0', '首页 ', '/home', NULL, 1, 'shouye', 1.0000000000, 'supadmin', '2021-07-03 10:53:37', NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES ('d54c4237df1440eea7089d134c901c80', '0', '地图', '/map', NULL, 1, 'mapmenu', 2.0000000000, 'supadmin', '2021-07-24 13:44:01', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `ID` char(32) NOT NULL,
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `URL` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATE_BY` char(32) NOT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对象存储配置表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` char(32) NOT NULL,
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `ROLE_NAME` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '备注',
  `CREATE_BY` char(32) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `ID` char(32) NOT NULL,
  `ROLE_ID` char(32) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` char(32) DEFAULT NULL COMMENT '菜单ID',
  `IS_HALF` tinyint(1) DEFAULT '0' COMMENT '节点是否半选',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';
-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` char(32) NOT NULL COMMENT '主键ID',
  `TENANT_ID` char(32) DEFAULT NULL COMMENT '租户ID',
  `PERSONNEL_ID` char(32) NOT NULL COMMENT '人员ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `SALT` varchar(20) DEFAULT NULL COMMENT '盐',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `STATUS` tinyint(4) DEFAULT NULL COMMENT '状态  0：停用   1：启用',
  `CREATE_BY` char(32) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_BY` char(32) DEFAULT NULL COMMENT '修改人ID',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `DELETED` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `AK_Key_2` (`USERNAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user,密码默认为Haidapu123#@!
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('supadmin', '', '1', 'supadmin', '7c325c6c7d70658497dbb6d1e97e0e5c0242569f2131a8a7deeb46ec5918681c', 'YzcmCZNvbXocrsz9dm8e', 'admin@admin.com', 1, '3', '2019-07-22 13:55:42', '3', '2019-07-22 13:55:42', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_data`;
CREATE TABLE `sys_user_data` (
  `ID` char(32) NOT NULL,
  `USER_ID` char(32) DEFAULT NULL COMMENT '用户ID',
  `EXT_ID` char(32) DEFAULT NULL COMMENT '外部ID',
  `EXT_TYPE` tinyint(4) DEFAULT NULL COMMENT '外部类型（10租户）',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户数据权限表';

-- ----------------------------
-- Records of sys_user_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `ID` char(32) NOT NULL,
  `USER_ID` char(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` char(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `USER_ID` char(32) NOT NULL,
  `TOKEN` varchar(100) NOT NULL COMMENT 'TOKEN',
  `CREATE_IP` varchar(80) DEFAULT NULL COMMENT '登录IP',
  `EXPIRE_TIME` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `UPDATE_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  UNIQUE KEY `TOKEN` (`TOKEN`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户token表';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
