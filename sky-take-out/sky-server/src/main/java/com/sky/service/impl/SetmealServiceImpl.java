package com.sky.service.impl;

import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import com.sky.service.ShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public Integer saveWithFlavor(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmeal.setStatus(StatusConstant.ENABLE);
        Integer result = setmealMapper.insert(setmeal);

        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes != null && setmealDishes.size() > 0){
            setmealDishes.forEach(setmealDish -> {
                setmealDish.setSetmealId(setmealId);
            });
            result = setmealDishMapper.insertBatch(setmealDishes);
        }

        return result;
    }
//
//    @Override
//    public void startOrStop(Integer status) {
//        redisTemplate.opsForValue().set("SHOP_STATUS",status);
//    }
//
//    @Override
//    public Integer getStatus() {
//        Integer shopStatus = (Integer) redisTemplate.opsForValue().get("SHOP_STATUS");
//        if(shopStatus == null){
//            return StatusConstant.ENABLE;
//        }
//        return shopStatus;
//    }


}
