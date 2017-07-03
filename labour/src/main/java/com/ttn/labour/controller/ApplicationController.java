package com.ttn.labour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {


    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String applicationMainPage() {

        return "<h1> Hello, from the Labour Application </h1>";
    }




}
