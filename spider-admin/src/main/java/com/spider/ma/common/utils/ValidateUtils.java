package com.spider.ma.common.utils;

/**
 * 验证数据合法性
 * 
 */
public class ValidateUtils extends org.springframework.beans.BeanUtils {

	public static boolean vidateIsEmpty(String value) {
		if(value==null||"".equals(value)){
			return true;
		}
		return false;
	}

	public static boolean vidateIsOverLength(String value, int length) {
		if(vidateIsEmpty(value)){
			return false;
		}else if(value.length()>length){
			return true;
		}
		return false;
	}
}
