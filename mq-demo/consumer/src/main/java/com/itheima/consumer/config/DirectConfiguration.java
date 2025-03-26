package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfiguration {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("hmall.direct");
//        return ExchangeBuilder.fanoutExchange("hmall.fanout").build();

    }

    @Bean
    public Queue directQueue1(){
        return new Queue("direct.queue1");
//        return QueueBuilder.durable("fanout.queue1").build();
    }

    @Bean
    public Binding directQueue1Binding(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("red");
    }
    @Bean
    public Queue directQueue2(){
        return new Queue("direct.queue2");
//        return QueueBuilder.durable("fanout.queue1").build();
    }

    @Bean
    public Binding directQueue2Binding(Queue directQueue2, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("blue");
    }
}
