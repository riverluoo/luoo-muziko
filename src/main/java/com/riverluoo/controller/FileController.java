package com.riverluoo.controller;

import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.base.Joiner;
import com.riverluoo.common.base.HttpResult;
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

    private static final String MARK = ".";
    private static final String MODULE = "luoo";

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public HttpResult upload(@RequestPart @RequestParam(required = false) MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            List<String> list = Arrays.asList(MODULE, IdWorker.get32UUID(), file.getOriginalFilename());
            String newName = Joiner.on("/").skipNulls().join(list);
            String bucketName = env.getProperty("aliyun.oss.bucketname");
            ossClient.putObject(bucketName, newName, file.getInputStream());
            String url = "https://" + bucketName + MARK + env.getProperty("aliyun.oss.endpoint") + "/" + newName;


            return HttpResult.success(url);
        }
        return HttpResult.success();
    }


}