package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.DictionaryDao;
import com.spider.ma.modules.sys.dao.TranslateDao;
import com.spider.ma.modules.sys.entity.DictionaryEntity;
import com.spider.ma.modules.sys.entity.TranslateEntity;
import com.spider.ma.modules.sys.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("dictionaryService")
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, DictionaryEntity> implements DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;
    @Autowired
    private TranslateDao translateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page = new Query(params).getPage();
        List<Map> maps = dictionaryDao.selectDictionaryType(page, params);
        maps.forEach(map -> map.put("list", queryInfoByCodeName(new DictionaryEntity() {{
            setCodeType((String) map.get("codeType"));
            setTenantId((String) map.get("tenantId"));
        }})));
        page.setRecords(maps);
        return new PageUtils(page);
    }


    @Override
    public List<DictionaryEntity> queryInfoByCodeName(DictionaryEntity dictionaryEntity) {
        /*if (!ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN)) {
            dictionaryEntity.setTenantId(ShiroUtils.getUserEntity().getTenantId());
        }*/
        dictionaryEntity.setLang(ShiroUtils.getUserEntity().getLang());
        return dictionaryDao.queryInfoByCodeName(dictionaryEntity);
    }

    @Override
    public void update(DictionaryEntity entity) {
        baseMapper.updateById(entity);
    }


    @Override
    public void save(DictionaryEntity entity) {
        entity.setState(BaseConstants.Status.ENABLE.key);
        entity.setDeleted(BaseConstants.Deleted.F.value);
        baseMapper.insert(entity);
    }

    @Override
    public void deleteByList(String[] ids) {
        dictionaryDao.update(new DictionaryEntity() {{
            setDeleted(BaseConstants.Deleted.T.value);
        }}, new EntityWrapper<DictionaryEntity>() {{
            in("ID", ids);
        }});
        translateDao.delete(new EntityWrapper<TranslateEntity>() {{
            where("EXT_TYPE = {0}", BaseConstants.ExtType.DICTIONARY.value);
            in("EXT_ID", ids);
        }});
    }
}
