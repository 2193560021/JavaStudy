package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishDTO;
import com.sky.result.PageResult;

public interface DishService {

//    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    Integer save(DishDTO dishDTO);
//
//    void setStatus(Integer status, Long id);
//
//    void update(CategoryDTO categoryDTO);
//
//    void delete(Long id);
}
