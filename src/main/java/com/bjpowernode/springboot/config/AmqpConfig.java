package com.bjpowernode.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xb
 * @Description: 定义rabbitMQ
 * @Date: 2020-07-05 13:40:17
 */
@Configuration
public class AmqpConfig {


    private static final Logger logger = LoggerFactory.getLogger(AmqpConfig.class);
    // 直连交换器
    public static final String SONG_EXCHANGE = "song_exchange";
    public static final String SONG_QUEUE = "song_queue";
    public static final String ROUTING_KEY = "songKey";


    //可用可不用
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
    @Bean(name = "RabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    RabbitTemplate myRabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //日后可用来做发送消息到服务器失败 进行人工调度等操作
                //情况发生场景:网络波动异常、服务器异常
                System.out.println("进来了" + correlationData.getId());
                if (!ack) {
                    logger.info("MQ消息发送失败，消息重发");
                }
            }
        });

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, DeadQueueConfig.DEAD_MESSAGE_EXCHANGE, DeadQueueConfig.DEAD_MESSAGE_ROUTING_KEY);
    }
}