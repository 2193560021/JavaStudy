package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {


    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     * @param dish
     * @return
     */
//    @Insert("insert into dish (name, category_id, price, image, description, create_time, update_time, create_user, update_user, status)" +
//            "values " + "#{name}, #{categoryId}, #{price}, #{image}, #{description}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status}")
    @AutoFill(value = OperationType.INSERT)
    Integer insert(Dish dish);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Select("select * from dish where id = #{id}")
    DishVO getByIdWithFlavor(Long id);

//    @Delete("delete from dish where id in (#{ids})")
    void deleteById(List<Long> ids);


    @Select("select status from dish where id = #{id}")
    Integer selectStatus(Long id);

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> listById(Integer categoryId);


    List<Dish> list(Dish dish);
}
