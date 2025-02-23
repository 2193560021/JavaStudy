package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

public interface SetmealService {
    Integer saveWithFlavor(SetmealDTO setmealDTO);

    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void setStatus(Integer status, Long id);

//    void startOrStop(Integer status);
//
//    Integer getStatus();
}
