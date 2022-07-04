package com.profiles.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {


    @Value("${my.message}")
    private String message;

    @GetMapping("msg")
    public String messageFromProperties(){
        return message;
    }

    @GetMapping("a")
    public String method(){
        return "Hi";
    }
}
