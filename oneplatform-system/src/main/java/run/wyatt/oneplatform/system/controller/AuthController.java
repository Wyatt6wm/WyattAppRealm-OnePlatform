package run.wyatt.oneplatform.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.wyatt.oneplatform.common.http.Data;
import run.wyatt.oneplatform.common.http.R;
import run.wyatt.oneplatform.system.model.constant.SysConst;
import run.wyatt.oneplatform.system.model.entity.Auth;
import run.wyatt.oneplatform.system.model.form.AuthForm;
import run.wyatt.oneplatform.system.service.AuthService;

import java.util.List;

/**
 * @author Wyatt
 * @date 2023/6/20 18:40
 */
@Slf4j
@Api(tags = "权限接口")
@RestController
@RequestMapping("/api/sys/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation("新增权限")
    @SaCheckLogin
    @SaCheckRole(SysConst.SUPER_ADMIN_ROLE_IDENTIFIER)
    @PostMapping("/addAuth")
    public R addAuth(@RequestBody AuthForm authForm) {
        log.info("请求参数: authForm={}", authForm);
        try {
            Assert.notNull(authForm, "请求参数为null");
            Assert.hasText(authForm.getIdentifier(), "权限标识符为空");
        } catch (Exception e) {
            log.info(e.getMessage());
            return R.fail("请求参数错误");
        }

        String identifier = authForm.getIdentifier();
        String name = authForm.getName();
        String description = authForm.getDescription();
        Boolean activated = authForm.getActivated();

        try {
            Auth auth = new Auth();
            auth.setIdentifier(identifier.trim());
            auth.setName((name != null) ? name.trim() : null);
            auth.setDescription((description != null) ? description.trim() : null);
            auth.setActivated(activated);
            log.info("组装后的新权限记录: {}", auth);

            log.info("新增权限");
            Auth result = authService.createAuth(auth);

            Data data = new Data();
            data.put("auth", result);
            return R.success(data);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @ApiOperation("删除权限")
    @SaCheckLogin
    @SaCheckRole(SysConst.SUPER_ADMIN_ROLE_IDENTIFIER)
    @GetMapping("/removeAuth")
    public R removeAuth(@RequestParam("id") Long authId) {
        log.info("请求参数: id={}", authId);
        try {
            Assert.notNull(authId, "权限ID为null");
        } catch (Exception e) {
            log.info(e.getMessage());
            return R.fail("请求参数错误");
        }

        try {
            log.info("删除权限");
            authService.removeAuth(authId);
            return R.success();
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @ApiOperation("编辑权限")
    @SaCheckLogin
    @SaCheckRole(SysConst.SUPER_ADMIN_ROLE_IDENTIFIER)
    @PostMapping("/editAuth")
    public R editAuth(@RequestBody AuthForm authForm) {
        log.info("请求参数: authForm={}", authForm);
        try {
            Assert.notNull(authForm, "请求参数为null");
            Assert.notNull(authForm.getId(), "权限ID为null");
        } catch (Exception e) {
            log.info(e.getMessage());
            return R.fail("请求参数错误");
        }

        Long id = authForm.getId();
        String identifier = authForm.getIdentifier();
        String name = authForm.getName();
        String description = authForm.getDescription();
        Boolean activated = authForm.getActivated();

        try {
            Auth auth = new Auth();
            auth.setIdentifier((identifier != null) ? identifier.trim() : null);
            auth.setName((name != null) ? name.trim() : null);
            auth.setDescription((description != null) ? description.trim() : null);
            auth.setActivated(activated);
            log.info("组装后的新权限记录: {}", auth);

            log.info("更新权限");
            Auth result = authService.updateAuth(id, auth);

            Data data = new Data();
            data.put("auth", result);
            return R.success(data);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @ApiOperation("获取权限列表")
    @SaCheckLogin
    @SaCheckRole(value = {SysConst.SUPER_ADMIN_ROLE_IDENTIFIER, SysConst.ADMIN_ROLE_IDENTIFIER}, mode = SaMode.OR)
    @GetMapping("/getAuthList")
    public R getAuthList() {
        try {
            log.info("查询全部权限");
            List<Auth> authList = authService.listAllAuths();

            Data data = new Data();
            data.put("authList", authList);
            return R.success(data);
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}
