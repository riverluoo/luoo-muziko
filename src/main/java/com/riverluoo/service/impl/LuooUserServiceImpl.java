package com.riverluoo.service.impl;

import com.riverluoo.entity.LuooUser;
import com.riverluoo.mapper.LuooUserMapper;
import com.riverluoo.service.LuooUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-26
 */
@Service
public class LuooUserServiceImpl extends ServiceImpl<LuooUserMapper, LuooUser> implements LuooUserService {

}
