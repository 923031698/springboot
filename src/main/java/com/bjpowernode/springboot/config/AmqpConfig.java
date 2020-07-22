package com.bjpowernode.springboot.config;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: bjb
 * @Description: 定义rabbitMQ
 * @Date: 2020-07-05 13:40:17
 */
@Configuration
public class AmqpConfig {

    public static final String EXCHANGE = "springboot.exchange";

    public static final String QUEUE = "springboot.queue";

    public static final String ROUTINGKEY = "springboot.routingkey";
    @org.springframework.beans.factory.annotation.Autowired
    private com.bjpowernode.springboot.mapper.goods.GoodsMapper goodsMapper;

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
        return QueueBuilder.durable(QUEUE).build();
    }

    /**
     * 路由绑定
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY);
    }

    /**
     * 定义消息转换实例
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }





}
