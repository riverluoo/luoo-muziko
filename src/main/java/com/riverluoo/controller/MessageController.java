package com.riverluoo.controller;

import com.aliyuncs.exceptions.ClientException;
import com.riverluoo.common.base.HttpResult;
import com.riverluoo.service.impl.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: wangyang
 * @since: 2019-09-24 14:43
 */
@Api(tags = "短信服务")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("获取验证码")
    @PostMapping("/verification/code/send")
    public HttpResult sendVerificationCode(@RequestParam(name = "phone") String phone) throws ClientException {
        this.messageService.sendVerificationCode(phone);

        return HttpResult.success();
    }

    @ApiOperation("校验验证码")
    @PostMapping("/verification/code/check")
    public HttpResult checkVerificationCode(@RequestParam("phone") String phone,
                                            @RequestParam("code") String code) {

        return HttpResult.success(this.messageService.checkVerificationCode(phone, code));
    }

}
