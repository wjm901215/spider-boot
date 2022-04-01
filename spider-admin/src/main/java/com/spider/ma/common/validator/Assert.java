package com.spider.ma.common.validator;

import com.spider.core.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }
}
