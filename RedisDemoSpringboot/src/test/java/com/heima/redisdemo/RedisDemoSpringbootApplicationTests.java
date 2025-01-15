package com.heima.redisdemo;

import com.heima.redisdemo.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoSpringbootApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name","胡歌");

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testSaveUSer() {
        redisTemplate.opsForValue().set("user:100",new User("虎哥", 21));

        User o = (User) redisTemplate.opsForValue().get("user:100");

        System.out.println(o);
    }
}
