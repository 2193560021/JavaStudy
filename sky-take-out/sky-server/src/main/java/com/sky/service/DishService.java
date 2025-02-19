package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;

public interface DishService {

//    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    Integer saveWithFlavor(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void setStatus(Integer status, Long id);

    Dish getById(Long id);

    void delete(Long id);
//
//    void setStatus(Integer status, Long id);
//
//    void update(CategoryDTO categoryDTO);
//
//    void delete(Long id);
}
