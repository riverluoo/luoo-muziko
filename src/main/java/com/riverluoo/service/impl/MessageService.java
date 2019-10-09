package com.riverluoo.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.riverluoo.common.exception.UserException;
import com.riverluoo.common.response.LoginResponse;
import com.riverluoo.entity.LuooUser;
import com.riverluoo.service.LuooUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @auther: wangyang
 * @since: 2019-09-24 14:45
 */
@Service
@Slf4j
public class MessageService {

    @Autowired
    private IAcsClient acsClient;
    @Autowired
    private LuooUserService luooUserService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String PREFIX = "SMS_V_";

    public void sendVerificationCode(String phone) throws ClientException {
        String code = getCode();
        SendSmsRequest req = new SendSmsRequest();
        req.setSignName("RiverLuoo");
        req.setTemplateCode("SMS_174810539");
        req.setPhoneNumbers(phone);
        Map<String, String> params = Collections.singletonMap("code", code);
        req.setTemplateParam(JSON.toJSONString(params));

        SendSmsResponse response = this.acsClient.getAcsResponse(req);
        String result = response.getMessage();
        log.info("手机: {},验证码发送结果:{}", phone, result);
        Assert.isTrue("OK".equals(response.getCode()), "超出发送频率，请稍后再试");

        this.redisTemplate.opsForValue().set(PREFIX + phone, code, 5, TimeUnit.MINUTES);

    }

    public LoginResponse checkVerificationCode(String phone, String code) {
        String value = this.redisTemplate.opsForValue().get(PREFIX + phone);
        boolean isPass = Objects.equals(code, value);
        if (isPass) {
            this.redisTemplate.delete(PREFIX + phone);
            String luooToken = UUID.randomUUID().toString().replaceAll("-", "");
            LuooUser luooUser = this.luooUserService.saveOrUpdate(phone);
            this.redisTemplate.opsForValue().set(luooToken, JSON.toJSONString(luooUser), 30, TimeUnit.MINUTES);

            return new LoginResponse(luooUser, luooToken);
        }

        throw new UserException("验证码错误");
    }


    private static String getCode() {
        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
        long min = (long) Math.pow(10, 3);
        return String.valueOf(threadRandom.nextLong(min, min * 10));
    }

}
