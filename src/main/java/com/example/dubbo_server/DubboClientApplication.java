package com.example.dubbo_server;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class DubboClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }

}
