package com.riverluoo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.entity.LuooVolume;
import com.riverluoo.service.LuooVolumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 落网期刊
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-23
 */
@Api(tags = "落网期刊")
@RestController
@RequestMapping("/luoo/volume")
public class LuooVolumeController {

    @Autowired
    private LuooVolumeService luooVolumeService;

    @ApiOperation("查询期刊")
    @GetMapping("/list")
    public HttpResult list(@ApiParam(name = "pageId", value = "起始页")
                           @RequestParam(required = false, defaultValue = "0") int pageId,
                           @ApiParam(name = "pageSize", value = "页长")
                           @RequestParam(required = false, defaultValue = "10") int pageSize,
                           @RequestParam(name = "id", required = false) @ApiParam("期刊编号") Integer id,
                           @RequestParam(name = "title", required = false) @ApiParam("期刊标题") String title) {

        Page<LuooVolume> page = new Page<>(pageId, pageSize);

        IPage<LuooVolume> volumeIPage = this.luooVolumeService.page(page, new QueryWrapper<LuooVolume>().eq(null != id, LuooVolume.ID, id).like(StringUtils.isNotBlank(title), LuooVolume.TITLE, title));
        return HttpResult.success(volumeIPage);

    }

    @ApiOperation("落网期刊-修改")
    @PutMapping("/update")
    public HttpResult update(@RequestBody LuooVolume luooVolume) {

        return HttpResult.success(this.luooVolumeService.updateById(luooVolume));
    }

}

