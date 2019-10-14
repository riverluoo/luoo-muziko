package com.riverluoo.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

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

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("test.topic");
    }

}
