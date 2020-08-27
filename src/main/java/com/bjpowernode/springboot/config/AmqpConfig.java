package com.bjpowernode.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @Author: xb
 * @Description: 定义rabbitMQ
 * @Date: 2020-07-05 13:40:17
 */
@Configuration
@AutoConfigureBefore({RabbitAutoConfiguration.class})
@ConditionalOnClass(EnableRabbit.class)
public class AmqpConfig {

    private RabbitProperties rabbitProperties;
    private ConnectionFactory connectionFactory;

    public SeeingMqAutoConfiguration(RabbitProperties rabbitProperties, ConnectionFactory connectionFactory) {
        this.rabbitProperties = rabbitProperties;
        this.connectionFactory = connectionFactory;

        this.rabbitProperties.getListener().getSimple().setAutoStartup(false);
        this.rabbitProperties.getListener().getSimple().getRetry().setEnabled(true);
        this.rabbitProperties.getTemplate().getRetry().setEnabled(true);
        this.rabbitProperties.setPublisherConfirms(true);
        if (connectionFactory instanceof CachingConnectionFactory) {
            ((CachingConnectionFactory) connectionFactory).setPublisherConfirms(true);
        }
    }



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
    RabbitTemplate myRabbitTemplate(ConnectionFactory connectionFactory, RabbitProperties rabbitProperties) {
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
        RabbitProperties.Template templateProperties = rabbitProperties.getTemplate();
        RabbitProperties.Retry retryProperties = templateProperties.getRetry();
        rabbitTemplate.setRetryTemplate(createRetryTemplate(retryProperties));
        if (retryProperties.isEnabled()) {
            rabbitTemplate.setRetryTemplate(createRetryTemplate(retryProperties));
        }
        if (templateProperties.getReceiveTimeout() != null) {
            rabbitTemplate.setReceiveTimeout(templateProperties.getReceiveTimeout().getSeconds() * 1000);
        }
        if (templateProperties.getReplyTimeout() != null) {
            rabbitTemplate.setReplyTimeout(templateProperties.getReplyTimeout().getSeconds() * 1000);
        }
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    private RetryTemplate createRetryTemplate(RabbitProperties.Retry properties) {
        RetryTemplate template = new RetryTemplate();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(properties.getMaxAttempts());
        template.setRetryPolicy(policy);
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(properties.getInitialInterval().getSeconds() * 1000);
        backOffPolicy.setMultiplier(properties.getMultiplier());
        backOffPolicy.setMaxInterval(properties.getMaxInterval().getSeconds() * 1000);
        template.setBackOffPolicy(backOffPolicy);
        return template;
    }
}