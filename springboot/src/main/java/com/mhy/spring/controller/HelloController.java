package com.mhy.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

public class HelloController {
    @Value("${persons.last-name}")
    private String name;
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World" ;//+ name;

    }

}
