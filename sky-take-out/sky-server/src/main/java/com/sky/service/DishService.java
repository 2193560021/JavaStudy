package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

//    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    Integer saveWithFlavor(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    void setStatus(Integer status, Long id);

    DishVO getById(Long id);

    void deleteBatch(List<Long> ids);

    void updateWithFlavor(DishDTO dishDTO);

    List<Dish> listById(Integer categoryId);


    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
//
//    void setStatus(Integer status, Long id);
//
//    void update(CategoryDTO categoryDTO);
//
//    void delete(Long id);
}
