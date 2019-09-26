package com.riverluoo.common.event;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riverluoo.common.event.business.UserEvent;
import com.riverluoo.entity.LuooUser;
import com.riverluoo.service.LuooUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户事件监听
 *
 * @auther: wangyang
 * @since: 2019-09-26 15:04
 */
@Component
public class UserEventListener {

    @Autowired
    private LuooUserService luooUserService;

    @EventListener
    public void handleUserLogin(UserEvent userEvent) {
        String phone = userEvent.getPhone();
        LuooUser luooUser = luooUserService.getOne(new QueryWrapper<LuooUser>().eq(LuooUser.PHONE, phone));
        if (Objects.isNull(luooUser)) {
            luooUser = new LuooUser();
            luooUser.setPhone(phone);
        }

        luooUserService.saveOrUpdate(luooUser);
    }


}
