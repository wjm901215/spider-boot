package com.spider.ma.modules.sys.service.impl;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.modules.sys.dao.CodeDao;
import com.spider.ma.modules.sys.entity.CodeEntity;
import com.spider.ma.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.spider.ma.modules.sys.service.CodeService;


@Service("codeService")
public class CodeServiceImpl extends ServiceImpl<CodeDao, CodeEntity> implements CodeService {

    @Autowired
    private CodeDao codeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CodeEntity> page = this.selectPage(
                new Query<CodeEntity>(params).getPage(),
                new EntityWrapper<CodeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CodeEntity getCodeByCityAndCodeType(Map<String, Object> map) {
        return codeDao.getCodeByCityAndCodeType(map);
    }

}
