package com.riverluoo.configuration;

import com.riverluoo.common.intercepter.AuthorizeInterceptor;
import com.riverluoo.common.intercepter.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther: wangyang
 * @since: 2019-09-29 10:05
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private AuthorizeInterceptor authorizeInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] notCheckUrls={
                "/luoo/song/list",
                "/luoo/volume/list"
        };


        registry.addInterceptor(logInterceptor);

        registry.addInterceptor(authorizeInterceptor)
                .addPathPatterns("/luoo/**")
                .excludePathPatterns(notCheckUrls);
    }
}
