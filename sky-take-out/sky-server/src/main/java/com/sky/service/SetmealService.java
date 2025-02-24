package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    Integer saveWithFlavor(SetmealDTO setmealDTO);

    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void setStatus(Integer status, Long id);

    SetmealVO getById(Long id);

    void updateWithFlavor(SetmealDTO setmealDTO);

    void deleteBatch(List<Long> ids);

//    void startOrStop(Integer status);
//
//    Integer getStatus();
}
