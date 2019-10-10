package com.riverluoo.common.intercepter;

import com.alibaba.fastjson.JSON;
import com.riverluoo.common.exception.AuthorizeException;
import com.riverluoo.entity.LuooUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @auther: wangyang
 * @since: 2019-10-09 15:23
 */
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String luooToken = request.getHeader("luoo-token");

        if(StringUtils.isBlank(luooToken)){
            luooToken=request.getParameter("luoo-token");
        }

        if (StringUtils.isBlank(luooToken)) {
            throw new AuthorizeException();
        }
        LuooUser luooUser = JSON.parseObject(this.redisTemplate.opsForValue().get(luooToken), LuooUser.class);
        if(Objects.isNull(luooUser)){
            throw new AuthorizeException();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
