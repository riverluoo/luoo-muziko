package com.riverluoo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.riverluoo.entity.LuooUser;
import com.riverluoo.mapper.LuooUserMapper;
import com.riverluoo.service.LuooUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-26
 */
@Service
@Transactional
public class LuooUserServiceImpl extends ServiceImpl<LuooUserMapper, LuooUser> implements LuooUserService {

    @Autowired
    private LuooUserMapper luooUserMapper;

    @Override
    public LuooUser saveOrUpdate(String phone) {
        LuooUser luooUser = luooUserMapper.selectOne(new QueryWrapper<LuooUser>().eq(LuooUser.PHONE, phone));
        if (Objects.isNull(luooUser)) {
            luooUser = new LuooUser();
            luooUser.setPhone(phone);
        }

        return luooUser;
    }
}
