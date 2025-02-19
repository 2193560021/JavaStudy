package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    PageResult page(CategoryPageQueryDTO categoryPageQueryDTO);

    Integer save(CategoryDTO categoryDTO);

    void setStatus(Integer status, Long id);

    void update(CategoryDTO categoryDTO);

    void delete(Long id);

    List<CategoryDTO> selectList(Integer type);
}
