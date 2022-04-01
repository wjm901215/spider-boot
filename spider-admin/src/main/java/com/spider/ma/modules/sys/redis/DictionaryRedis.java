package com.spider.ma.modules.sys.redis;

import com.spider.core.common.utils.RedisKeys;
import com.spider.core.common.utils.RedisUtil;
import com.spider.ma.modules.sys.dao.DictionaryDao;
import com.spider.ma.modules.sys.entity.DictionaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据字典缓存
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.sys.redis.DictionaryRedis,v 0.1 2021/7/7 19:23 Exp $$
 */
@Component
public class DictionaryRedis {
    @Autowired
    private RedisUtil redisUtils;
    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * 根据字典类别和字典值获取字典
     * @param codeType 字典类别
     * @param codeValue 字典值
     * @return
     */
    public DictionaryEntity getDictionaryByCodeAndValue(String codeType, String codeValue){
        String key = RedisKeys.getSysConfigKey(codeType+codeValue);
        DictionaryEntity dictionaryEntity = (DictionaryEntity) redisUtils.get(key);
        if (null == dictionaryEntity) {
            dictionaryEntity = dictionaryDao.selectOne(new DictionaryEntity() {{
                setCodeType(codeType);
                setCodeValue(codeValue);
            }});
            saveOrUpdate(dictionaryEntity);
        }
        return dictionaryEntity;
    }
    /**
     * 根据字典类别和字典值获取字典
     * @param codeType 字典类别
     * @param codeValue 字典值
     * @return
     */
    public DictionaryEntity getDictionaryByCodeAndValue(String codeType,Integer codeValue){
        String codeValueStr=String.valueOf(codeValue);
        String key = RedisKeys.getSysConfigKey(codeType+codeValueStr);
        DictionaryEntity dictionaryEntity = (DictionaryEntity) redisUtils.get(key);
        if (null == dictionaryEntity) {
            dictionaryEntity = dictionaryDao.selectOne(new DictionaryEntity() {{
                setCodeType(codeType);
                setCodeValue(codeValueStr);
            }});
            saveOrUpdate(dictionaryEntity);
        }
        return dictionaryEntity;
    }

    /**
     * 获取数据字典描述
     * @param codeType 字典类别
     * @param codeValue 字典值
     * @return
     */
    public String getTextByCodeAndValue(String codeType,Integer codeValue){
        DictionaryEntity dictionaryByCodeAndValue = this.getDictionaryByCodeAndValue(codeType, codeValue);
        if(dictionaryByCodeAndValue!=null){
            return dictionaryByCodeAndValue.getCodeText();
        }
        return "";
    }

    /**
     * 获取数据字典描述
     * @param codeType 字典类别
     * @param codeValue 字典值
     * @return
     */
    public String getTextByCodeAndValue(String codeType,String codeValue){
        DictionaryEntity dictionaryByCodeAndValue = this.getDictionaryByCodeAndValue(codeType, codeValue);
        if(dictionaryByCodeAndValue!=null){
            return dictionaryByCodeAndValue.getCodeText();
        }
        return "";
    }
    private void saveOrUpdate(DictionaryEntity dictionary) {
        if (dictionary == null) {
            return;
        }
        String key = RedisKeys.getSysDictionaryKey(dictionary.getCodeType()+dictionary.getCodeValue());
        redisUtils.set(key, dictionary);
    }
}
