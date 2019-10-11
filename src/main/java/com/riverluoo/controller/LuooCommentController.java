package com.riverluoo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooCollection;
import com.riverluoo.entity.LuooComment;
import com.riverluoo.entity.LuooReply;
import com.riverluoo.service.LuooCommentService;
import com.riverluoo.service.LuooReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author wangyangyang
 * @since 2019-10-11
 */
@Api(tags = "期刊评论")
@RestController
@RequestMapping("/luoo/comment")
public class LuooCommentController {

    @Autowired
    private LuooCommentService luooCommentService;
    @Autowired
    private LuooReplyService luooReplyService;

    @ApiOperation("新增评论")
    @PutMapping
    public HttpResult insert(@RequestBody LuooComment luooComment) {

        return HttpResult.success(this.luooCommentService.save(luooComment));
    }

    @ApiOperation("查询评论")
    public HttpResult list(@ApiParam(name = "pageId", value = "起始页")
                           @RequestParam(required = false, defaultValue = "0") int pageId,
                           @ApiParam(name = "pageSize", value = "页长")
                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                           @RequestParam(name = "id", required = false) @ApiParam("期刊编号") Integer id){

        Page<LuooComment> page = new Page<>(pageId, pageSize);

        IPage<LuooComment> commentIPage = this.luooCommentService.page(page, new QueryWrapper<LuooComment>().eq(LuooComment.TOPIC_ID, id).orderByDesc(LuooComment.CREATE_TIME));
        commentIPage.getRecords().forEach(x->{
            List<LuooReply> luooReplies = this.luooReplyService.list(new QueryWrapper<LuooReply>().eq(LuooReply.COMMENT_ID, x.getId()).orderByDesc(LuooReply.CREATE_TIME));

        });

        return HttpResult.success(commentIPage);
    }

}

