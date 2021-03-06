package com.riverluoo.service;

import com.riverluoo.common.response.UserDetailResponse;
import com.riverluoo.entity.LuooUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author wangyangyang
 * @since 2019-09-26
 */
public interface LuooUserService extends IService<LuooUser> {

    /**
     * 创建或更新用户
     *
     * @param phone 手机号码
     * @return
     */
    LuooUser saveOrUpdate(String phone);

    /**
     * 查询当前用户id
     *
     * @return userId
     */
    String getUserId();

    /**
     * 查询用户详情
     *
     * @param id 用户id
     * @return
     */
    UserDetailResponse getUserDetail(String id);

}
