package com.riverluoo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.riverluoo.common.response.UserDetailResponse;
import com.riverluoo.entity.LuooUser;
import com.riverluoo.mapper.LuooUserMapper;
import com.riverluoo.service.LuooUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public LuooUser saveOrUpdate(String phone) {
        LuooUser luooUser = luooUserMapper.selectOne(new QueryWrapper<LuooUser>().eq(LuooUser.PHONE, phone));
        if (Objects.isNull(luooUser)) {
            luooUser = new LuooUser();
            luooUser.setPhone(phone);
        }

        return luooUser;
    }

    @Override
    public String getUserId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            return "system";
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String luooToken = request.getHeader("luoo-token");
        if (StringUtils.isBlank(luooToken)) {
            return "system";
        }
        String userEntity = this.redisTemplate.opsForValue().get(luooToken);
        LuooUser luooUser = JSON.parseObject(userEntity, LuooUser.class);
        if (Objects.nonNull(luooUser)) {
            return luooUser.getId();
        }
        return "system";
    }

    @Override
    public UserDetailResponse getUserDetail(String id) {
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        LuooUser luooUser = this.luooUserMapper.selectById(id);
        userDetailResponse.setLuooUser(luooUser);
        if (Objects.nonNull(luooUser) && Objects.nonNull(luooUser.getCreateTime())) {
            Date createTime = luooUser.getCreateTime();
            int days = (int) ((new Date().getTime() - createTime.getTime()) / (1000 * 3600 * 24));
            userDetailResponse.setRegisterDay(days);
        }

        return userDetailResponse;
    }

}
