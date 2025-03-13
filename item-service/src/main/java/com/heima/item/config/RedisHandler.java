package com.heima.item.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.item.pojo.Item;
import com.heima.item.pojo.ItemStock;
import com.heima.item.service.IItemStockService;
import com.heima.item.service.impl.ItemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisHandler implements InitializingBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ItemService itemService;

    @Autowired
    private IItemStockService stockService;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化缓存
        List<Item> itemList = itemService.list();

        for (Item item : itemList) {
            String json = MAPPER.writeValueAsString(item);

            stringRedisTemplate.opsForValue().set("item:id" + item.getId(), json);
        }

        List<ItemStock> stockList = stockService.list();

        for (ItemStock itemStock : stockList) {
            String json = MAPPER.writeValueAsString(itemStock);

            stringRedisTemplate.opsForValue().set("item:stock:id" + itemStock.getId(), json);
        }

    }
}
