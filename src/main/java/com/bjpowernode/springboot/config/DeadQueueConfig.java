package com.bjpowernode.springboot.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;
@Configuration
public class DeadQueueConfig {
    public static final String DEAD_MESSAGE_EXCHANGE = "dead_message_exchange";
    public static final String DEAD_MESSAGE_QUEUE = "dead_message_queue";
    public static final String DEAD_MESSAGE_ROUTING_KEY = "dead_message_routing_key";

    // 死信交换器
    @Bean
    DirectExchange deadMessageExchange() {
        return new DirectExchange(DEAD_MESSAGE_EXCHANGE);
    }

    // 死信队列
    @Bean
    Queue deadQueue() {
        return new Queue(DEAD_MESSAGE_QUEUE);
    }

    @Bean
    Binding deadBind() {
        return BindingBuilder.bind(deadQueue()).to(deadMessageExchange()).with(DEAD_MESSAGE_ROUTING_KEY);
    }


}