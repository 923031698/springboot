package com.bjpowernode.springboot.message;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.springboot.config.AmqpConfig;
import com.bjpowernode.springboot.model.domian.elasticsearch.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * @Author: bjb
 * @Description:
 * @Date: 2020-07-05 13:52:00
 */
@Log4j2
@Component
public class MessageProducer {


    @Autowired
    RabbitTemplate rabbitTemplate;

    /* @Autowired
     private RabbitTemplate rabbitTemplate;

    @PostConstruct
     public void init() {
         rabbitTemplate.setConfirmCallback(confirmCallback);            //指定 ConfirmCallback
         rabbitTemplate.setReturnCallback(returnCallback);             //指定 ReturnCallback

     }*/
    public void sendMessage(Student student) {
        String orderId = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(JSONObject.toJSONString(student));
//        rabbitTemplate.setConfirmCallback(confirmCallback);
//        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend(AmqpConfig.SONG_EXCHANGE, AmqpConfig.ROUTING_KEY, student, correlationData);
    }

    /**
     * 回调函数: confirm确认
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData:{}; ack:{}; cause:{}", correlationData, ack, cause);
            if (!ack) {
                log.error("回调函数: confirm确认异常correlationData:{}; ack:{}; cause:{}", correlationData, ack, cause);
            }
        }
    };

    /**
     * 回调函数: return返回
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            log.info("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };


}
