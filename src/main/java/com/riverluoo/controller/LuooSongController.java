package com.riverluoo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooSong;
import com.riverluoo.service.LuooSongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 落网歌曲
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-23
 */
@Api(tags = "落网歌曲")
@RestController
@RequestMapping("/luoo/song")
public class LuooSongController {

    @Autowired
    private LuooSongService luooSongService;

    @ApiOperation("落网歌曲-列表")
    @GetMapping("/list")
    public HttpResult list(@RequestParam(name = "volume") @ApiParam("期刊编号") Integer volume) {
        List<LuooSong> list = this.luooSongService.list(new QueryWrapper<LuooSong>().eq(LuooSong.VOLUME_ID, volume).orderByAsc(LuooSong.SEQUENCE));

        return HttpResult.success(list);
    }


    @ApiOperation("落网歌曲-修改")
    @PutMapping("/update")
    public HttpResult update(@RequestBody LuooSong luooSong) {
        return HttpResult.success(this.luooSongService.updateById(luooSong));
    }


}

