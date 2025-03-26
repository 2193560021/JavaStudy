package com.itheima.publisher;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue(){
        String queueName = "simple.queue";
        String message = "hello, Spring AMQP";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSimpleQueue1(){
        String queueName = "simple.queue";
        String message = "hello, Spring AMQP111";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue1(){
        String queueName = "work.queue";
        int m = 50;
        while(m -- > 0){
            String message = "hello, Spring AMQP" + (50 - m);
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }

    @Test
    public void testFanoutQueue1(){
        String exchangeName = "hmall.fanout";
        String message = "hello, everyone";
        rabbitTemplate.convertAndSend(exchangeName, null, message);
    }

    @Test
    public void testDirectQueue1(){
        String exchangeName = "hmall.direct";
        String message = "hello, everyone";
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testTopicQueue1(){
        String exchangeName = "hmall.topic";
        String message = "hello, China!";
        rabbitTemplate.convertAndSend(exchangeName, "japan.news", message);
    }



    @Test
    public void testObjectQueue(){
        Map<String, Object> msg = new HashMap<>(2);
        msg.put("name","Jack123");
        msg.put("age", 21);
        rabbitTemplate.convertAndSend("object.queue", msg);
    }

}