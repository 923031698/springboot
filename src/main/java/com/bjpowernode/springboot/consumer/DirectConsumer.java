package com.bjpowernode.springboot.consumer;

import com.bjpowernode.springboot.model.elasticsearch.Student;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者，实现一个消息监听器
 */
@Component
public class DirectConsumer {

    /**
     * 当有消息的时候就会回调该方法
     */
    @RabbitListener(queues = "springboot.queue")
    public void onMessage(Student student) {
        System.out.println("DirectConsumer接收到的消息--> " + student);
    }
}