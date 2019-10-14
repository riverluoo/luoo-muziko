package com.riverluoo.jms.consumer;

import com.alibaba.fastjson.JSON;
import com.riverluoo.entity.LuooLog;
import com.riverluoo.service.LuooLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @auther: wangyang
 * @since: 2019-10-12 16:22
 */
@Component
@Slf4j
public class MessageConsumer {

    @Autowired
    private LuooLogService luooLogService;

    @JmsListener(destination = "log.queue")
    void consume(String message) {
        log.info("message:{}", message);
        LuooLog luooLog = JSON.parseObject(message, LuooLog.class);
        boolean save = this.luooLogService.save(luooLog);
        log.info("result:{}", save);

    }

}
