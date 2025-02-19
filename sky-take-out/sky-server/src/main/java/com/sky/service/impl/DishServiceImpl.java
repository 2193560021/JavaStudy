package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

//    @Autowired
//    private DishMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

//    @Override
//    public PageResult page(CategoryPageQueryDTO categoryPageQueryDTO) {
//
//        //分页查询
//        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
//        Page<Category> page1 = categoryMapper.pageQuery(categoryPageQueryDTO);
//        long total = page1.getTotal();
//        List<Category> records = page1.getResult();
//        PageResult page = new PageResult(total, records);
//
//        return page;
//    }

    @Override
    public Integer save(DishDTO dishDTO) {
//
//        System.out.println("当前线程id" + Thread.currentThread().getId());
//        Dish dish = new Dish();
//
//        //对象属性拷贝
//        BeanUtils.copyProperties(dishDTO,dish);
//
//        //设置账号状态，默认正常状态
////        category.setStatus(StatusConstant.ENABLE);
//        //设置创建时间和修改时间
////        category.setCreateTime(LocalDateTime.now());
////        category.setUpdateTime(LocalDateTime.now());
//        // 设置创建人和修改人
////        category.setCreateUser(BaseContext.getCurrentId());
////        category.setUpdateUser(BaseContext.getCurrentId());
//
//
////        Integer insert = categoryMapper.insert(category);
////        return insert;
        return null;
    }

//    @Override
//    public void setStatus(Integer status, Long id) {
//        Category category = Category.builder()
//                .status(status)
////                .updateTime(LocalDateTime.now())
////                .updateUser(BaseContext.getCurrentId())
//                .id(id)
//                .build();
//        categoryMapper.update(category);
//    }
//
//    @Override
//    public void update(CategoryDTO categoryDTO) {
//        Category category = new Category();
//        BeanUtils.copyProperties(categoryDTO,category);
////        category.setUpdateTime(LocalDateTime.now());
////        category.setUpdateUser(BaseContext.getCurrentId());
//        categoryMapper.update(category);
//    }
//
//    @Override
//    public void delete(Long id) {
//        //查询当前分类是否关联了菜品
//        Integer count = dishMapper.countByCategoryId(id);
//        if (count > 0){
//            throw new RuntimeException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
//        }
//
//        //查询当前分类是否关联了套餐
//        count = setmealMapper.countByCategoryId(id);
//        if (count > 0){
//            throw new RuntimeException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
//        }
//        //正常删除
//        categoryMapper.deleteById(id);
//    }


}
