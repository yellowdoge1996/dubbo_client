package com.example.dubbo_server;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo_server.api.HelloWorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rimuli123
 */
@RestController
public class HelloConsumer {
    @Reference(url = "dubbo://192.168.1.103:20880")
    private HelloWorldService helloWorldService;

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name){
        return helloWorldService.sayHello(name);
//        return name;
    }
}
