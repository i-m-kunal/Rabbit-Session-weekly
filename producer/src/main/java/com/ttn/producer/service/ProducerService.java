package com.ttn.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.producer.util.constants.AppConstants;
import com.ttn.producer.util.dto.QueueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {


    private AmqpTemplate amqpTemplate;
    private FanoutExchange myFanoutExchange;

    @Autowired
    public void setMyFanoutExchange(FanoutExchange fanoutExchange) {
        this.myFanoutExchange = fanoutExchange;
    }

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

        amqpTemplate.convertAndSend(myFanoutExchange.getName(), "", jsonString);
        log.info("Pusher pushed into " + AppConstants.MY_FANOUT_EXCHANGE);
        log.info("Info Pushed");
        log.info(jsonString);
        return true;
    }
}
