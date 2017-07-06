package com.ttn.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttn.producer.util.constants.AppConstants;
import com.ttn.producer.util.dto.QueueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {


    private AmqpTemplate amqpTemplate;
    private TopicExchange myTopicExchange;

    @Autowired
    public void setMyTopicExchange(TopicExchange topicExchange) {
        this.myTopicExchange = topicExchange;
    }

    @Autowired
    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {

        this.amqpTemplate = amqpTemplate;
    }


    Logger log = LoggerFactory.getLogger(this.getClass());

    public Boolean pushIntoDirectExchange(String value, String routingKey)
    {
        QueueDTO queueDTO = new QueueDTO(value);
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(queueDTO);
        } catch (JsonProcessingException e) {

            log.error("Json Processing Exception Occurred" + e.getMessage());
            e.printStackTrace();
        }
        log.info(jsonString);


        amqpTemplate.convertAndSend(myTopicExchange.getName(), routingKey, jsonString);

        return true;
    }
}
