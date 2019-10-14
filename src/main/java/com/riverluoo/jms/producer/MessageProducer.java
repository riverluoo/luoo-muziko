package com.riverluoo.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;

/**
 * @auther: wangyang
 * @since: 2019-10-14 09:24
 */
@Service
public class MessageProducer {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     *
     * @param destination 消息发送地址
     * @param message     消息内容
     */
    public void sendMessage(Destination destination, String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 发送消息，queue是发送到的队列，message是待发送的消息
     *
     * @param message 消息内容
     */
    public void sendMessage(final String message) {
        jmsTemplate.convertAndSend(this.queue, message);
    }


}
