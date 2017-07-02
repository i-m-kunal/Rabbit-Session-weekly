package com.ttn.worker.config;

import com.ttn.worker.util.constants.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by kunal on 27/5/17.
 */
@Configuration
public class ApplicationConfig {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean(name = AppConstants.HELLO_WORLD_QUEUE)
    public Queue getHelloWorldQueue() {

        log.info("Queue Registered :" + AppConstants.HELLO_WORLD_QUEUE);
        return new Queue(AppConstants.HELLO_WORLD_QUEUE);

    }



}
