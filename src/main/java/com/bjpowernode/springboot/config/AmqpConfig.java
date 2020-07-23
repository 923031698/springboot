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

    public static final String EXCHANGE = "springboot.exchange";  //直连交换机

    public static final String QUEUE = "springboot.queue";      //直连队列

    public static final String ROUTINGKEY = "springboot.routingkey";  //直连路由


    public static final String DEAD_EXCHANGE = "dead.exchange";  //死信交换机

    public static final String DEAD_QUEUE = "dead.queue";       //死信队列

    public static final String DEAD_ROUTINGKEY = "dead.routingkey"; //死信路由


    /**
     * 声明一个直连交换机
     */
    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANGE).durable(true).build();
    }

    /**
     * 声明一个队列
     */
    @Bean
    public Queue queue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        params.put("x-dead-letter-routing-key", DEAD_ROUTINGKEY);
        return new Queue(QUEUE, true, false, false, params);


    }
    /**
     * 路由绑定
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTINGKEY);
    }

    /**
     * 死信交换机
     */
    @Bean
    public DirectExchange deadExchange() {
        return ExchangeBuilder.directExchange(DEAD_EXCHANGE).durable(true).build();
    }

    /**
     * 死信队列
     */
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    /**
     * 死信路由
     */
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(DEAD_ROUTINGKEY);
    }


    /**
     * 定义消息转换实例
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
