package com.heima.test;

import com.heima.jedis.util.JedisConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;

    @Before
    public void setUp() throws Exception {
        jedis = JedisConnectionFactory.getJedis();

        jedis.auth("123456");
        jedis.select(0);
    }

    @Test
    public void testString() {
        String set = jedis.set("name", "虎哥");
        System.out.println("set = " + set);

        String name = jedis.get("name");

        System.out.println("name = " + name);

    }

    @Test
    public void testHash() {
        jedis.hset("user:1","name","Jack");
        jedis.hset("user:1","age","21");

        Map<String, String> stringStringMap = jedis.hgetAll("user:1");
        System.out.println("stringStringMap = " + stringStringMap);
    }

    @After
    public void tearDown() throws Exception {
        if(jedis != null){
            jedis.close();
        }
    }
}
