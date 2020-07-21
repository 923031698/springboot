package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.config.AmqpConfig;
import com.bjpowernode.springboot.consumer.DirectConsumer;
import com.bjpowernode.springboot.message.MessageProducer;
import com.bjpowernode.springboot.common.utils.Response;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-07-19 21:16:08
 * @Company: 乐木几网络科技有限公司
 */
@Log4j2
@RestController
public class RabbitMqController {
    @Autowired
    MessageProducer messageProducer;

    @Autowired
    DirectConsumer directConsumer;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/message")
    public Response message(){
        Student student =new Student();
        student.setId("213");
        student.setAge(12);
        amqpTemplate.convertAndSend(AmqpConfig.EXCHANGE, AmqpConfig.ROUTINGKEY,student);
        return ResponseUtils.success();
    }

    @RequestMapping("/direct")
    public Response direct(Message message){
       // directConsumer.onMessage(message);
        return ResponseUtils.success();
    }
}
