package com.lyy.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class KafkaConsumerTest {
    public static void main(String[] args) {

        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "lyy");
        

        //创建消费者对象

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);

        //获取数据
        consumer.subscribe(Collections.singletonList("test"));


        //通过生产者发送数据到kafka
        while (true) {
            //获取数据
            ConsumerRecords<String, String> datas = consumer.poll((100));
            for (ConsumerRecord<String, String> data : datas) {
                System.out.println("data = " + data);
            }

        }

    }
}
