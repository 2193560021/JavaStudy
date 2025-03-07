package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void insertBatch(List<DishFlavor> flavors);

//    @Delete("delete from dish_flavor where dish_id in (#{id})")
    void deleteByDishId(List<Long> ids);


    @Delete("delete from dish_flavor where id = #{id}")
    void deleteById(Long id);

    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> getFlavorByDishId(Long id);
}
