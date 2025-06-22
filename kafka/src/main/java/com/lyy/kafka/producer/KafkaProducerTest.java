package com.lyy.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lyy
 * @create 2024-01-08 19:57
 * @description TODO 刘宇阳的第一个kafka生产者
 * 刘宇阳
 */

public class KafkaProducerTest {
    public static void main(String[] args) {

        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        //创建生产者对象

        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);

        //创建数据



        //通过生产者发送数据到kafka

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(
                    "test",
                    "hello" + i,
                    "world" + i
            );
            producer.send(record);
        }

        //关闭生产者
        producer.close();
    }
}
