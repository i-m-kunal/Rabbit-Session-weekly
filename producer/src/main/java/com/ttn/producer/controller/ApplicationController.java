package com.ttn.producer.controller;

import com.ttn.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {


    @Autowired
    ProducerService producerService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String applicationMainPage() {

        return "<h1> Hello, from the producer Application </h1>";
    }


    @RequestMapping(value = "/pushIntoDirectExchangeQueue", produces = "application/json")
    public String pushIntoDirectExchange(@RequestParam String value,@RequestParam String routingKey) {

        producerService.pushIntoDirectExchange(value,routingKey);
        return "Data Pushed Into Hello World Queue";

    }

}
