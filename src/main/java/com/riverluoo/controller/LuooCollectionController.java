package com.riverluoo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooCollection;
import com.riverluoo.service.LuooCollectionService;
import com.riverluoo.service.LuooUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收藏歌曲和期刊 前端控制器
 * </p>
 *
 * @author wangyangyang
 * @since 2019-10-09
 */
@Api(tags = "收藏歌曲和期刊")
@RestController
@RequestMapping("/luoo/collection")
public class LuooCollectionController {


    @Autowired
    private LuooCollectionService luooCollectionService;
    @Autowired
    private LuooUserService luooUserService;

    @ApiOperation("收藏歌曲")
    @PostMapping("/song")
    public HttpResult likeSong(@RequestParam(name = "songId") @ApiParam("歌曲编号") Integer songId) {

        return HttpResult.success(this.luooCollectionService.insertOrDeleteSong(songId));
    }

    @ApiOperation("收藏歌单")
    @PostMapping("/volume")
    public HttpResult likeVolume(@RequestParam(name = "volumeId") @ApiParam("期刊编号") Integer volumeId) {

        return HttpResult.success(this.luooCollectionService.insertOrDeleteVolume(volumeId));
    }

    @ApiOperation("收藏列表")
    @GetMapping("/list")
    public HttpResult list(@ApiParam(name = "pageId", value = "起始页")
                           @RequestParam(required = false, defaultValue = "0") int pageId,
                           @ApiParam(name = "pageSize", value = "页长")
                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                           @RequestParam(name = "song", required = false) @ApiParam("音乐") String song,
                           @RequestParam(name = "volume", required = false) @ApiParam("期刊") String volume) {

        Page<LuooCollection> page = new Page<>(pageId, pageSize);

        IPage<LuooCollection> luooCollectionIPage = this.luooCollectionService.page(page, new QueryWrapper<LuooCollection>()
                .eq(LuooCollection.USER_ID, luooUserService.getUserId())
                .isNotNull(StringUtils.isNotBlank(song), LuooCollection.SONG_ID)
                .isNotNull(StringUtils.isNotBlank(volume), LuooCollection.VOLUME_ID)
                .orderByDesc(LuooCollection.CREATE_TIME));

        return HttpResult.success(luooCollectionIPage);

    }

}

