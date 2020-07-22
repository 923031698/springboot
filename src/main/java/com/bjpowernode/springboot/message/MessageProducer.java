package com.bjpowernode.springboot.message;

import com.bjpowernode.springboot.config.AmqpConfig;
import com.bjpowernode.springboot.model.elasticsearch.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: bjb
 * @Description: //TODO
 * @Date: 2020-07-05 13:52:00
 * @Company: 乐木几网络科技有限公司
 */
@Log4j2
@Component
public class MessageProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public  void  sendMessage(){
        Student student =new Student();
        student.setId("213");
        student.setAge(12);
        amqpTemplate.convertAndSend(AmqpConfig.EXCHANGE, AmqpConfig.ROUTINGKEY,student.toString());
        log.info("发送消息:"+student.toString());
    }

}
