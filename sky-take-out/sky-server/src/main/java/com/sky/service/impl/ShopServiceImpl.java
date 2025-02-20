package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.service.ShopService;
import com.sky.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void startOrStop(Integer status) {
        redisTemplate.opsForValue().set("SHOP_STATUS",status);
    }

    @Override
    public Integer getStatus() {
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get("SHOP_STATUS");
        if(shopStatus == null){
            return StatusConstant.ENABLE;
        }
        return shopStatus;
    }
}
