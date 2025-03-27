package com.itheima.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
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



    @Test
    public void testConfirmCallback() throws InterruptedException {
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("spring amqp处理确认结果异常:{}", ex);
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                //判断是否成功
                if (result.isAck()){
                    log.debug("收到ConfirmCallback ack, 消息发送成功");
                }else {

                    log.debug("收到ConfirmCallback ack, 消息发送失败，reason:{}", result.getReason());
                }
            }
        });

        String exchangeName = "hmall.direct";
        String message = "hello, China!";
        rabbitTemplate.convertAndSend(exchangeName, "blue", message, cd);

        Thread.sleep(100);
    }

}