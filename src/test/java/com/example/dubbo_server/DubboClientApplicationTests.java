package com.example.dubbo_server;

import com.example.dubbo_server.controller.HelloConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboClientApplicationTests {
@Autowired
private HelloConsumer helloConsumer;
    @Test
    public void contextLoads() {
//        System.out.println(dubboProperties.toString());
    }

}
