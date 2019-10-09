package com.riverluoo.controller;


import com.riverluoo.common.base.HttpResult;
import com.riverluoo.service.LuooCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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

}

