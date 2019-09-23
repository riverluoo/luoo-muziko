package com.riverluoo.controller;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.base.Joiner;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.common.base.UploadType;
import com.riverluoo.entity.LuooSong;
import com.riverluoo.entity.LuooVolume;
import com.riverluoo.service.LuooSongService;
import com.riverluoo.service.LuooVolumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @auther: wangyang
 * @since: 2019-09-23 17:17
 */
@Api(tags = "文件上传")
@RestController
public class FileController {

    @Autowired
    private Environment env;
    @Autowired
    private OSSClient ossClient;
    @Autowired
    private LuooVolumeService luooVolumeService;
    @Autowired
    private LuooSongService luooSongService;

    private static final String MARK = ".";
    private static final String MODULE = "luoo";

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(@RequestPart @RequestParam(required = false) MultipartFile file,
                         @RequestParam(required = false) UploadType uploadType,
                         @RequestParam(required = false) Integer volumeId,
                         @RequestParam(required = false) Integer songId) throws Exception {
        if (!file.isEmpty()) {
            List<String> list = Arrays.asList(MODULE, IdWorker.get32UUID(), file.getOriginalFilename());
            String newName = Joiner.on("/").skipNulls().join(list);
            String bucketName = env.getProperty("aliyun.oss.bucketname");
            ossClient.putObject(bucketName, newName, file.getInputStream());
            String url = new StringBuilder().append(
                    "https://").append(bucketName + MARK + env.getProperty("aliyun.oss.endpoint")).append("/").append(newName).toString();

            switch (uploadType){
                case Volume:
                    LuooVolume luooVolume = new LuooVolume();
                    luooVolume.setUrl(url);
                    luooVolume.setId(volumeId);
                    this.luooVolumeService.updateById(luooVolume);
                    break;
                case SongMusic:
                    LuooSong luooSong = new LuooSong();
                    luooSong.setMusic(url);
                    this.luooSongService.update(luooSong,new UpdateWrapper<LuooSong>().eq(LuooSong.VOLUME_ID,volumeId).eq(LuooSong.SEQUENCE,songId));
                    break;
                case SongUrl:
                    LuooSong luooSongUrl = new LuooSong();
                    luooSongUrl.setUrl(url);
                    this.luooSongService.update(luooSongUrl,new UpdateWrapper<LuooSong>().eq(LuooSong.VOLUME_ID,volumeId).eq(LuooSong.SEQUENCE,songId));
                    break;
                    default:
                        break;
            }


            return HttpResult.success(url);

        }
        return HttpResult.success("");
    }


}