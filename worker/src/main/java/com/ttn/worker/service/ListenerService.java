package com.ttn.worker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.worker.util.constants.AppConstants;
import com.ttn.worker.util.dto.QueueDTO;
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

    @RabbitListener(queues = AppConstants.WORKER_QUEUE)
    public Integer helloWorldListener(Message message) throws InterruptedException {

        Thread.sleep(3000);
        String jsonString = new String(message.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        QueueDTO queueDTO = new QueueDTO();
        try {
            queueDTO = objectMapper.readValue(jsonString, QueueDTO.class);
        } catch (Exception e) {
        }
        log.info("Message Received");
        log.info(jsonString);
        return queueDTO.getValue().length();
    }
}
