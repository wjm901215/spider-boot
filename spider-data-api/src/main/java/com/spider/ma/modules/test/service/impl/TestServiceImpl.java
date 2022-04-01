package com.spider.ma.modules.test.service.impl;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.db.base.entity.VillageEntity;
import com.spider.ma.db.base.service.DbVillageService;
import com.spider.ma.modules.test.modle.req.TestRequest;
import com.spider.ma.modules.test.modle.resp.TestResponse;
import com.spider.ma.modules.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试Service接口实现
 *
 * @author SpiderMan
 * @version 1.0.0: com.spider.ma.modules.base.service.impl.TestServiceImpl,v 0.1 2021/7/28 12:36 Exp $$
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Autowired
    private DbVillageService dbVillageService;
    /**
     * todo
     * @param testRequest 请求实体
     * @return
     */
    @Override
    public TestResponse getVillageData(TestRequest testRequest) {
        Map paramMap = new HashMap(4){{
            put("page",testRequest.getPageNo().toString());
            put("limit",testRequest.getPageSize().toString());
        }};
        PageUtils pageUtils = dbVillageService.queryPage(paramMap);
        List<VillageEntity> listVillage = (List<VillageEntity>) pageUtils.getList();
        List<TestResponse.Village> list =new ArrayList<TestResponse.Village>();
        listVillage.stream().forEach(validate->{
            TestResponse.Village villageRes = TestResponse.Village.builder().villCode(validate.getVillCode()).villName(validate.getVillName()).address(validate.getAddress()).build();
            list.add(villageRes);
        });
        return TestResponse.builder().list(list).build();
    }
}
