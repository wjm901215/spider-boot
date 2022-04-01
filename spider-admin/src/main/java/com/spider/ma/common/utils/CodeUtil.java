package com.spider.ma.common.utils;


import com.spider.ma.modules.sys.entity.CodeEntity;

public class CodeUtil {



	public static String getTheCode(CodeEntity code) {
		Long maxCode = code.getMaxCode();
		int length = code.getMaxLen();
		String codeStr = (maxCode + 1) + "";
		code.setMaxCode(code.getMaxCode()+1);
		int lack = length - codeStr.length();
		while (lack != 0) {
			codeStr = "0" + codeStr;
			lack--;
		}
		if (code.getCodeType().equals(SysConst.CODE_TYPE_AREA_CODE)
				|| code.getCodeType().equals(SysConst.CODE_TYPE_CITY_CODE)
				|| code.getCodeType().equals(SysConst.CODE_TYPE_STREET_CODE)){
			return codeStr;
		}else{
			return code.getPrefix() + code.getCityCode() + codeStr;
		}
	}
}
