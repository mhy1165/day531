package com.mhy.spring.springboot02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class HelloWorld {

    @ResponseBody  //将java对象转为json格式的数据。
    @RequestMapping("/hello")
    public String Hello(){

        return  "Hello World";
    }

    @RequestMapping("/success")
    public String Success(Map<String,Object > map){
        map.put("hello","你好");
        return "success";
    }
}
