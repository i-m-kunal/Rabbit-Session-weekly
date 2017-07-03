package com.ttn.producer.config;

import com.ttn.producer.util.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by kunal on 27/5/17.
 */
@Configuration
public class ApplicationConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean(name = AppConstants.WORKER_QUEUE)
    public Queue getWorkerQueue() {

        log.info("Queue Registered :" + AppConstants.WORKER_QUEUE);
        return new Queue(AppConstants.WORKER_QUEUE);
    }


    @Bean(name = AppConstants.LABOUR_QUEUE)
    public Queue getLabourQueue() {

        log.info("Queue Registered :" + AppConstants.LABOUR_QUEUE);
        return new Queue(AppConstants.LABOUR_QUEUE);
    }


    @Bean(name = AppConstants.MY_FANOUT_EXCHANGE)
    public FanoutExchange getMyFanoutExchange() {
        return new FanoutExchange(AppConstants.MY_FANOUT_EXCHANGE);
    }


    @Bean
    Binding binding1() {
        return BindingBuilder.bind(getWorkerQueue()).to(getMyFanoutExchange());
    }

    @Bean
    Binding binding2() {
        return BindingBuilder.bind(getLabourQueue()).to(getMyFanoutExchange());
    }
}
