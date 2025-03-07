package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    // "select setmeal_id from setmeal_dish where dish_id in #{dishIds}")
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);


    Integer insertBatch(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getDishBySetmealId(Long id);

    void deleteBySetmealId(List<Long> ids);
}
