package com.heima.redisdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.redisdemo.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name","胡歌");

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testSaveUSer() throws JsonProcessingException {
        User user = new User("虎哥", 21);

        String s = mapper.writeValueAsString(user);

        redisTemplate.opsForValue().set("user:200",s);


        String s1 = redisTemplate.opsForValue().get("user:200");


        User user1 = mapper.readValue(s, User.class);
        System.out.println("user1 = " + user1);
    }

    @Test
    void testHash() {
        redisTemplate.opsForHash().put("user:400","name","虎哥");
        redisTemplate.opsForHash().put("user:400","age","23");

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("user:400");

        System.out.println("entries = " + entries);

    }
}
