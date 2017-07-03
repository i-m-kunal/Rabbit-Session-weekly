package com.ttn.labour.service;

import com.ttn.labour.util.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@EnableRabbit
public class ListenerService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = AppConstants.LABOUR_QUEUE)
    public void helloWorldListener(Message message) throws InterruptedException {

        Thread.sleep(2000);
        String jsonString = new String(message.getBody());
        log.info("Message Received");
        log.info(jsonString);

    }
}
