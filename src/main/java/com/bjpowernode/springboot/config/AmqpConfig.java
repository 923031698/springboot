package com.bjpowernode.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @Author: xb
 * @Description: 定义rabbitMQ
 * @Date: 2020-07-05 13:40:17
 */
@Configuration
public class AmqpConfig {

    @Autowired
    RabbitProperties properties;


    private static final Logger logger = LoggerFactory.getLogger(AmqpConfig.class);
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


    @Bean(name = "RabbitTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    RabbitTemplate myRabbitTemplate(ConnectionFactory connectionFactory, RetryTemplate retryTemplate) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("进来了" + correlationData.getId());
                if (!ack) {
                    logger.info("MQ消息发送失败，消息重发");
                }
            }
        });
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setRetryTemplate(rabbitRetryTemplate());
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
    public RetryTemplate rabbitRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // 设置监听（不是必须）
        retryTemplate.registerListener(new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
                // 执行之前调用 （返回false时会终止执行）
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                // 重试结束的时候调用 （最后一次重试 ）
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                //  异常 都会调用
                logger.error("-----第{}次调用", retryContext.getRetryCount());
            }
        });

        // 个性化处理异常和重试 （不是必须）
        /* Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        //设置重试异常和是否重试
        retryableExceptions.put(AmqpException.class, true);
        //设置重试次数和要重试的异常
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(5,retryableExceptions);*/
        retryTemplate.setBackOffPolicy(backOffPolicyByProperties());
        retryTemplate.setRetryPolicy(retryPolicyByProperties());
        return retryTemplate;
    }



    @Bean
    public ExponentialBackOffPolicy backOffPolicyByProperties() {
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        long maxInterval = properties.getListener().getSimple().getRetry().getMaxInterval().getSeconds();
        long initialInterval = properties.getListener().getSimple().getRetry().getInitialInterval().getSeconds();
        double multiplier = properties.getListener().getSimple().getRetry().getMultiplier();
        // 重试间隔
        backOffPolicy.setInitialInterval(initialInterval * 1000);
        // 重试最大间隔
        backOffPolicy.setMaxInterval(maxInterval * 1000);
        // 重试间隔乘法策略
        backOffPolicy.setMultiplier(multiplier);
        return backOffPolicy;
    }

    @Bean
    public SimpleRetryPolicy retryPolicyByProperties() {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        int maxAttempts = properties.getListener().getSimple().getRetry().getMaxAttempts();
        retryPolicy.setMaxAttempts(maxAttempts);
        return retryPolicy;
    }


}