package com.riverluoo.common.intercepter;

import com.alibaba.fastjson.JSON;
import com.riverluoo.common.util.RequestUtil;
import com.riverluoo.entity.LuooLog;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @auther: wangyang
 * @since: 2019-09-29 09:55
 */
@Component
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        LuooLog luooLog = new LuooLog();

        luooLog.setStartTime(new Date());
        luooLog.setUri(request.getRequestURI());
        luooLog.setMethod(request.getMethod());
        luooLog.setUrl(JSON.toJSONString(request.getRequestURL()));
        luooLog.setUserAgent(request.getHeader("User-Agent"));
        luooLog.setBasePath(RequestUtil.getBasePath(request));
        luooLog.setIp(RequestUtil.getIpAddr(request));
        luooLog.setUsername(String.valueOf(request.getAttribute("userAccount")));
        luooLog.setParameter(JSON.toJSONString(request.getParameterMap()));

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(ApiOperation.class)) {
            ApiOperation methodAnnotation = handlerMethod.getMethodAnnotation(ApiOperation.class);
            luooLog.setDescription(methodAnnotation.value());
        }

        this.publisher.publishEvent(luooLog);
    }
}
