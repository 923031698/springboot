package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.common.utils.ResponseUtils;
import com.bjpowernode.springboot.consumer.DirectConsumer;
import com.bjpowernode.springboot.message.MessageProducer;
import com.bjpowernode.springboot.model.common.Response;
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
@RestController
public class RabbitMqController {
    @Autowired
    MessageProducer messageProducer;

    @Autowired
    DirectConsumer directConsumer;

    @RequestMapping("/message")
    public Response message(){
        messageProducer.sendMessage("test");
        return ResponseUtils.success();
    }

    @RequestMapping("/direct")
    public Response direct(Message message){
       // directConsumer.onMessage(message);
        return ResponseUtils.success();
    }
}
