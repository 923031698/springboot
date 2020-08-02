package com.bjpowernode.springboot.message;

import com.bjpowernode.springboot.config.AmqpConfig;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: bjb
 * @Description:
 * @Date: 2020-07-05 13:52:00
 */
@Log4j2
@Component
public class MessageProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public  void  sendMessage(Student student){
        amqpTemplate.convertAndSend(AmqpConfig.SONG_EXCHANGE, AmqpConfig.ROUTING_KEY,student);
     //   log.info("发送消息:"+student.toString());
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功:" + correlationData);
        } else {
            System.out.println("消息发送失败:" + cause);
        }


    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息主体message: " + message);
        System.out.println("消息主体message: " + replyCode);
        System.out.println("描述: " + replyText);
        System.out.println("消息使用的交换器exchange: " + exchange);
        System.out.println("消息使用的路由键routing: " + routingKey);

    }
}
