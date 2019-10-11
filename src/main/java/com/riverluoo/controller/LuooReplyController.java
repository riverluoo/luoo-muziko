package com.riverluoo.controller;


import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooReply;
import com.riverluoo.service.LuooReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 回复表 前端控制器
 * </p>
 *
 * @author wangyangyang
 * @since 2019-10-11
 */
@Api(tags = "评论回复")
@RestController
@RequestMapping("/luoo/reply")
public class LuooReplyController {

    @Autowired
    private LuooReplyService luooReplyService;

    @ApiOperation("新增回复")
    @PutMapping
    public HttpResult insert(@RequestBody LuooReply luooReply) {

        return HttpResult.success(this.luooReplyService.save(luooReply));

    }

}

