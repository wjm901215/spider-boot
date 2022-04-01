package com.spider.ma.modules.sys.controller;

import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.modules.sys.entity.AreaEntity;
import com.spider.ma.modules.sys.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 区域表
 *
 * @author SpiderMan
 * @email professorX@mail.com
 * @date 2021-06-06 15:33:41
 */
@RestController
@RequestMapping("sys/area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:area:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = areaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:area:info")
    public R info(@PathVariable("id") String id) {
        AreaEntity area = areaService.selectById(id);

        return R.ok().put("area", area);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:area:save")
    public R save(@RequestBody AreaEntity area) {
        areaService.insert(area);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:area:update")
    public R update(@RequestBody AreaEntity area) {
        areaService.update(area);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:area:delete")
    public R delete(@RequestBody String[] ids) {
        areaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("queryCascadeList")
    public R queryCascadeList(@RequestBody(required = false) AreaEntity entity) {
        return R.ok().put("data", areaService.queryCascadeList(entity));
    }

    /**
     * 获取机构级联
     */
    @RequestMapping("/getAreaOptions")
    @RequiresPermissions("sys:area:info")
    public R getAreaOptions() {
        List<Map> area = areaService.selectAreaOptions();
        return R.ok().put("record", area);
    }

    @RequestMapping(value = "/upload2",method = RequestMethod.POST)
    public String upload2(HttpServletRequest request) throws IOException, ServletException {
        String path= "/Users/wangjunma/Downloads/2021-11-20/";
        ServletInputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = request.getInputStream();

            fileOutputStream = new FileOutputStream(new File(path+"/20210729130156.jpg"));

            int len;
            byte[] bytes = new byte[1024];
            while((len = inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        finally {
            if(fileOutputStream!=null){
                fileOutputStream.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return "上传成功";
    }
}
