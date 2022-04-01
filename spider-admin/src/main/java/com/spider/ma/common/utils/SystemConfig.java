package com.spider.ma.common.utils;

/**
 * 系统参数枚举类
 * <p/>
 * Created by GJW on 2017/2/22.
 */
public class SystemConfig {
    /*系统文件存放的Web访问路径*/
    public static String WEB_URL = "webUrl";
    /*webUrl 下载路径*/
    public static String DIR_ROOT = "dirRoot";
    /*MsgPro的传输路径 */
    public static String PUSH_MSG_URL = "pushMsgUrl";
    /*MsgPro的传输路径 */
    public static String MPUSH_MSG_URL = "Mpush_Url";
    public static String NOTIFY_URL = "notify_url";
    /*短信配置*/
    public static String SMS_SERVER_PORT = "smsServerPort";
    public static String SMS_SERVER_IP = "smsServerIp";
    public static String SMS_ACCOUNT = "smsAccount";
    public static String LOOPSTEP = "loopStep";
    /*驶离退费通知地址*/
    public static String RETURN_URL = "returnUrl";

    /*停车记录同步通知地址、中间件查询车牌信息地址*/
    public static String SYNC_URL = "syncUrl";

    /*APP有效续买时间*/
    public static String EXPIRY_TIME = "expiryTime";

    /*市民卡数据类型 0 正式 1测试*/
    public static String CITIZEN_CARD_DATA_TYPE = "citizenCardDataType";

    /*t跳变异常短信手机号*/
    public static String JUMP_PHONE = "jumpPhone";

    /*t跳变异常短信发送间隔*/
    public static String JUMP_MESSAGE_INTERVAL = "jumpMessageInterval";

    /*地感重复间隔*/
    public static String EVENT_INTERVAL = "eventInterval";

    /*本地ftp的地址*/
    public static String FTP_URL = "ftpUrl";

    /*地感设备账号*/
    public static String EVENT_ACCOUNT = "eventAccount";

    /*文件上传方式*/
    public static String UPLOAD_METHOD= "uploadMethod";

}
