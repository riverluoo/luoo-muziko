package com.riverluoo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riverluoo.entity.LuooUser;
import com.riverluoo.service.LuooUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: wangyang
 * @since: 2019-09-26 16:27
 */
@Service
public class LoginService {
    @Autowired
    private LuooUserService luooUserService;

    /**
     * 登录
     *
     * @param phone 手机号
     * @return
     */
    public LuooUser login(String phone) {
        LuooUser luooUser = this.luooUserService.getOne(new QueryWrapper<LuooUser>().eq(LuooUser.PHONE, phone));

        return luooUser;
    }

}
