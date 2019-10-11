package com.riverluoo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooLog;
import com.riverluoo.service.LuooLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-29
 */
@Api(tags = "系统日志")
@RestController
@RequestMapping("/luoo/log")
public class LuooLogController {

    @Autowired
    private LuooLogService luooLogService;

    @ApiOperation("查询日志列表")
    @GetMapping
    public HttpResult list(@ApiParam(name = "pageId", value = "起始页")
                           @RequestParam(required = false, defaultValue = "0") int pageId,
                           @ApiParam(name = "pageSize", value = "页长")
                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                           @RequestParam(name = "startTime", required = false) @ApiParam("开始时间") String startTime,
                           @RequestParam(name = "endTime", required = false) @ApiParam("结束时间") String endTime) {

        Page<LuooLog> page = new Page<>(pageId, pageSize);
        IPage<LuooLog> logIPage = this.luooLogService.page(page, new QueryWrapper<LuooLog>()
                .between(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime), LuooLog.CREATE_TIME, startTime, endTime)
                .orderByDesc(LuooLog.CREATE_TIME));

        return HttpResult.success(logIPage);

    }


}

