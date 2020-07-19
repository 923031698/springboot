package com.bjpowernode.springboot;

import com.bjpowernode.springboot.message.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application  implements CommandLineRunner {

    @Autowired
    private MessageProducer messageProducer;

    public static void main(String[] args) {

        //启动spring容器 + 启动一个内嵌的Tomcat
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        messageProducer.sendMessage("spring boot rabbitmq");
    }
}