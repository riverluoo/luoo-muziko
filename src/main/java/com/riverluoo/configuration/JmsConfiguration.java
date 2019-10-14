package com.riverluoo.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @auther: wangyang
 * @since: 2019-10-12 16:19
 */
@EnableJms
@Configuration
public class JmsConfiguration {

    @Bean
    public Queue queue(){
        return new ActiveMQQueue("log.queue");
    }


}
