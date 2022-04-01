package com.spider.ma.modules.sys.excel.rule;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spider.ma.modules.sys.entity.ConfigEntity;
import com.spider.ma.modules.sys.excel.impt.ConfigData;
import com.spider.ma.modules.sys.service.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import static com.spider.ma.common.enumerate.BaseConstants.ParamTypeEnum.APP;
import static com.spider.ma.common.enumerate.BaseConstants.ParamTypeEnum.BACK_STAGE;

/**
 * 导入规则校验
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.excel.rule.ConfigDataRule,v 0.1 2021/7/20 11:43 Exp $$
 */
@Component
public class ImportRule {
    final static String CHECK_REQUIRED_FIELDS = "请检查必填项";

    final static String CHECK_TYPE_MUST1OR2 = "配置类型必须是1或者2";

    final static String DUPLICATE_PARAM = "参数名称重复";

    /**
     * 参数配置导入项必填项验证
     *
     * @return false 验证未通过，true 验证通过
     */
    public boolean validateMustImport(ConfigData configData, ConfigService configService) {

        if (StringUtils.isEmpty(configData.getParamKey()) || ObjectUtil.isEmpty(configData.getType()) || StringUtils.isEmpty(configData.getParamValue())) {
            configData.setErrorMsg(CHECK_REQUIRED_FIELDS);
            return false;
        }
        if (APP.key != configData.getType() && BACK_STAGE.key != configData.getType()) {
            configData.setErrorMsg(CHECK_TYPE_MUST1OR2);
            return false;
        }
        ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("PARAM_KEY", configData.getParamKey()));
        if (ObjectUtil.isNotEmpty(configEntity)) {
            configData.setErrorMsg(DUPLICATE_PARAM);
            return false;
        }
        return true;
    }
}
