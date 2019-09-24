package com.riverluoo.configuration;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @auther: wangyang
 * @since: 2019-09-24 14:53
 */
@Configuration
public class MessageConfiguration {

    @Bean
    public IAcsClient iAcsClient(Environment environment) throws ClientException {
        String accessKey = environment.getProperty("aliyun.accessKeyId");
        String secretKey = environment.getProperty("aliyun.accessKeySecret");
        String domain = environment.getProperty("aliyun.domain");
        String product = environment.getProperty("aliyun.product");
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",product,domain);
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, secretKey);

        return new DefaultAcsClient(profile);

    }
}
