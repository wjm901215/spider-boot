package com.spider.ma.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.Query;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.modules.sys.dao.TenantDao;
import com.spider.ma.modules.sys.entity.TenantEntity;
import com.spider.ma.modules.sys.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("tenantService")
public class TenantServiceImpl extends ServiceImpl<TenantDao, TenantEntity> implements TenantService {
    @Autowired
    private TenantDao tenantDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page = new Query(params).getPage();
        page.setRecords(tenantDao.page(page, params));
        return new PageUtils(page);
    }


    @Override
    public boolean update(TenantEntity entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean insert(TenantEntity entity) {
        entity.setCreateBy(ShiroUtils.getUserId());
        return super.insert(entity);
    }

    @Override
    public void deleteBatchIds(List<String> ids) {
        tenantDao.deleteBatchIds(ids);
    }

    @Override
    public List<Map<String, Object>> queryTenantForUser(String id) {
        return tenantDao.selectMaps(new EntityWrapper<TenantEntity>(){{
            where("ID in (select sud.EXT_ID from sys_user_data sud where EXT_TYPE = {0} and USER_ID = {1})", BaseConstants.DataPermissionExtType.TENANT.key, id);
        }});
    }

    @Override
    public List<TenantEntity> queryTenantList() {
        return tenantDao.selectList(new EntityWrapper<TenantEntity>() {{
            /*if (!ShiroUtils.getUserId().equals(BaseConstants.SUPER_ADMIN)) {
                where("ID in (select sud.EXT_ID from sys_user_data sud where EXT_TYPE = {0} and USER_ID = {1}) ",
                        BaseConstants.DataPermissionExtType.TENANT.key, ShiroUtils.getUserId());
            }*/
        }});
    }
}
