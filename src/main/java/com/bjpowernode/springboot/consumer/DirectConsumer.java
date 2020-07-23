package com.bjpowernode.springboot.consumer;

import com.bjpowernode.springboot.config.AmqpConfig;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者，实现一个消息监听器
 */
@Component
public class DirectConsumer {

    /**
     * 直连队列
     */
    @RabbitListener(queues = AmqpConfig.QUEUE)
    @RabbitHandler
    public void onMessage(Student student, Channel channel, Message message) throws Exception {
        try {
            System.out.println("直连队列收到消息--> " + student);
            int a=10/0;
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

    /**
     * 死信队列
     */
    @RabbitListener(queues = AmqpConfig.DEAD_QUEUE)
    @RabbitHandler
    public void deadQueueMessage(Student student , Channel channel, Message message) throws IOException {
        try {
            System.out.println("死信队列收到消息--> " + student);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }


}