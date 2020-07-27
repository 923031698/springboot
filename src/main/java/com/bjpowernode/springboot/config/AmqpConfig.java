package com.bjpowernode.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: bjb
 * @Description: 定义rabbitMQ
 * @Date: 2020-07-05 13:40:17
 */
@Configuration
public class AmqpConfig {

    // 直连交换器
    public static final String SONG_EXCHANGE = "song_exchange";
    public static final String SONG_QUEUE = "song_queue";
    public static final String ROUTING_KEY = "songKey";

    // 创建一个队列, 如果存在死信时则交由死信交换器转存到死信队列
    @Bean
    public Queue queue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", DeadQueueConfig.DEAD_MESSAGE_EXCHANGE);
        params.put("x-dead-letter-routing-key", DeadQueueConfig.DEAD_MESSAGE_ROUTING_KEY);
        return new Queue(SONG_QUEUE, true, false, false, params);
    }

    // 创建一个直连交换器
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(SONG_EXCHANGE);
    }

    // 绑定
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(ROUTING_KEY);
    }

    /**
     * 定义消息转换实例
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
