package com.spider.ma.modules.sys.service.impl;

import com.spider.ma.common.enumerate.BaseConstants;
import com.spider.ma.modules.sys.dao.MenuDao;
import com.spider.ma.modules.sys.dao.UserDao;
import com.spider.ma.modules.sys.dao.UserDataDao;
import com.spider.ma.modules.sys.dao.UserTokenDao;
import com.spider.ma.modules.sys.entity.MenuEntity;
import com.spider.ma.modules.sys.entity.UserDataEntity;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.core.common.utils.ThreadVariable;
import com.spider.ma.modules.sys.entity.UserTokenEntity;
import com.spider.ma.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author SpiderMan
 */
@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenDao userTokenDao;
    @Autowired
    private UserDataDao userDataDao;

    @Override
    public Set<String> getUserPermissions(String userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId.equals(BaseConstants.SUPER_ADMIN)) {
            List<MenuEntity> menuList = menuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for (MenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = userDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public UserTokenEntity queryByToken(String token) {
        return userTokenDao.queryByToken(token);
    }

    @Override
    public UserEntity queryUser(String userId) {
        UserEntity userEntity = userDao.queryByUserId(userId);
        List<UserDataEntity> userDataEntity = userDataDao.queryUserDataByUserId(userEntity.getId());
        userEntity.setUserDataEntity(userDataEntity);
        //线程变量设置语言
        ThreadVariable.getInstance().put(com.spider.core.common.constant.Constant.LANG, userEntity.getLang());
        return userEntity;
    }
}
