package com.riverluoo.controller;


import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooUser;
import com.riverluoo.service.LuooUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-26
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/luoo/user")
public class LuooUserController {

    @Autowired
    private LuooUserService luooUserService;

    @ApiOperation("用户-修改")
    @PutMapping("/update")
    public HttpResult update(@RequestBody LuooUser luooUser) {

        return HttpResult.success(this.luooUserService.updateById(luooUser));
    }

    @ApiOperation("用户详情")
    @GetMapping("/detail")
    public HttpResult detail(@RequestParam(name = "id") String id) {

        return HttpResult.success(this.luooUserService.getUserDetail(id));
    }

}

