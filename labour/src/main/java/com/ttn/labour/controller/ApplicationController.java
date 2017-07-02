package com.ttn.labour.controller;

import com.ttn.labour.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {


    @Autowired
    ProducerService producerService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String applicationMainPage() {

        return "<h1> Hello, from the Labour Application </h1>";
    }


    @RequestMapping(value = "/pushIntoHelloWorld", produces = "application/json")
    public String pushInHelloWorldQueue(@RequestParam String value) {

        producerService.pushIntoHelloWorldQueue(value);
        return "Data Pushed Into Hello World Queue";
    }


    @RequestMapping(value = "/helloWorld", method = RequestMethod.POST, produces = "application/json")
    public Boolean helloWorldPusher(@RequestParam String value) {
        System.out.println(">>>" + value);
        return true;
    }


}
