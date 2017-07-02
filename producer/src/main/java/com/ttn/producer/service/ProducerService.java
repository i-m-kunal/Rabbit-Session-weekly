package com.ttn.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.producer.util.constants.AppConstants;
import com.ttn.producer.util.dto.QueueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private AmqpTemplate amqpTemplate;


    @Autowired
    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }


    Logger log = LoggerFactory.getLogger(this.getClass());

    public Boolean pushIntoHelloWorldQueue(String value) {

        QueueDTO queueDTO = new QueueDTO(value);
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(queueDTO);
        } catch (JsonProcessingException e) {

            log.error("Json Processing Exception Occurred" + e.getMessage());
            e.printStackTrace();
        }

//        amqpTemplate.receiveAndConvert();

        amqpTemplate.convertAndSend(AppConstants.HELLO_WORLD_QUEUE, jsonString);
        log.info("Pusher pushed into " + AppConstants.HELLO_WORLD_QUEUE);
        log.info("Info Pushed");
        log.info(jsonString);
        return true;
    }
}
