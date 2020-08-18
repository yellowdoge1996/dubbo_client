package com.example.dubbo_server.controller;

import com.example.dubbo_server.api.HelloWorldService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rimuli123
 */
@RestController
public class HelloConsumer {
    @DubboReference(version = "1.0.0")
    private HelloWorldService helloWorldService;

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name){
        return helloWorldService.sayHello(name);
    }
}
