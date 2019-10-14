package com.riverluoo.jms.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @auther: wangyang
 * @since: 2019-10-14 11:27
 */
@Component
@Slf4j
public class TopicConsumer {

    @JmsListener(destination = "test.topic")
    public void receive1(String text) {
        log.info("test.topic 消费者:receive1=" + text);
    }

    @JmsListener(destination = "test.topic")
    public void receive2(String text) {
        log.info("test.topic 消费者:receive2=" + text);
    }

    @JmsListener(destination = "test.topic")
    public void receive3(String text) {
        log.info("test.topic 消费者:receive3=" + text);
    }

}
