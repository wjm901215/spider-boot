package com.spider.ma.modules.sys.controller;

import com.alibaba.excel.EasyExcel;
import com.spider.ma.common.annotation.SysLog;
import com.spider.ma.common.utils.PageUtils;
import com.spider.ma.common.utils.R;
import com.spider.ma.common.utils.ShiroUtils;
import com.spider.ma.common.validator.Assert;
import com.spider.ma.common.validator.ValidatorUtils;
import com.spider.ma.common.validator.group.AddGroup;
import com.spider.ma.common.validator.group.UpdateGroup;
import com.spider.ma.modules.sys.entity.UserEntity;
import com.spider.ma.modules.sys.excel.expt.UserDataTemplate;
import com.spider.ma.modules.sys.form.PasswordForm;
import com.spider.ma.modules.sys.service.TenantService;
import com.spider.ma.modules.sys.service.UserRoleService;
import com.spider.ma.modules.sys.service.UserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.spider.ma.modules.sys.enumerate.SysConstants.UserResponseEnum.PASSWORD_IS_INCORRECT;

/**
 * 系统用户
 *
 * @author SpiderMan
 * @email SpiderMan@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private TenantService tenantService;


    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public R password(@RequestBody PasswordForm form) {
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");
        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
        //更新密码
        boolean flag = userService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error(PASSWORD_IS_INCORRECT.key,PASSWORD_IS_INCORRECT.value);
        }
        return R.ok();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public R info(@PathVariable("userId") String userId) {
        UserEntity user = userService.selectById(userId);
        //获取用户所属的角色列表
        List<String> roleIdList = userRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return R.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public R save(@RequestBody UserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);
        user.setCreateBy(getUserId());
        userService.save(user);
        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R update(@RequestBody UserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);
        user.setCreateBy(getUserId());
        userService.update(user);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public R delete(@RequestBody String[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return R.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, getUserId())) {
            return R.error("当前用户不能删除");
        }
        userService.deleteBatch(userIds);
        return R.ok();
    }


    @RequestMapping("/getUserInfo")
    public R getUserInfo() {
        return R.ok().put("data", new HashMap() {{
            put("jobType", ShiroUtils.getUserEntity().getJobType());
        }});
    }


    @RequestMapping("/switchLanguage")
    public R switchLanguage(String lang) {
        userService.switchLanguage(lang);
        return R.ok();
    }

    /**
     * 修改当前登录用户tenantId
     */
    @RequestMapping("/switchTenantId/{tenantId}")
    public R switchTenantId(@PathVariable("tenantId") String tenantId){
        userService.switchTenantId(tenantId);
        return R.ok();
    }

    @RequestMapping("/queryUserDetail")
    public R queryUserDetail(@RequestBody UserEntity user) {
        UserEntity userEntity = userService.queryUserDetail(user);
        //获取用户所属的角色列表
        List<String> roleIdList = userRoleService.queryRoleIdList(user.getId());
        userEntity.setRoleIdList(roleIdList);
        //获取用户的租户
        List<String> tenantIds = new LinkedList();
        tenantService.queryTenantForUser(user.getId()).forEach(t -> tenantIds.add((String) t.get("id")));
        userEntity.setTenantIds(tenantIds);
        return R.ok().put("data", userEntity);
    }

    @RequiresPermissions("sys:user:update")
    @RequestMapping("/disableUser")
    public R disableUser(@RequestBody String[] userIds) {
        userService.disableUser(userIds);
        return R.ok();
    }


    @RequiresPermissions("sys:user:update")
    @RequestMapping("/enableUser")
    public R enableUser(@RequestBody String[] userIds) {
        userService.enableUser(userIds);
        return R.ok();
    }


    @RequiresPermissions("sys:user:update")
    @RequestMapping("/restPwd")
    public R restPwd(@RequestBody UserEntity user) {
        userService.restPwd(user);
        return R.ok();
    }

    @RequestMapping("/resignation")
    @RequiresPermissions("sys:user:delete")
    public R resignation(@RequestBody String[] userIds){
        userService.resignation(userIds);
        return R.ok();
    }

    /**
     * 用户数据导出
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link UserDataTemplate}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response,@RequestParam Map<String, Object> params) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("用户数据", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserDataTemplate.class).sheet("用户数据").doWrite(userService.queryExportData(params));
    }

}
