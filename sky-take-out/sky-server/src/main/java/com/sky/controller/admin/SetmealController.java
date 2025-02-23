package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    /**
     * 套餐分页查询
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询：{}",setmealPageQueryDTO);
        PageResult page1 = setmealService.page(setmealPageQueryDTO);
        return Result.success(page1);
    }

    /**
     *新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public Result<String> save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}",setmealDTO);

        System.out.println("当前线程id" + Thread.currentThread().getId());
        Integer save = setmealService.saveWithFlavor(setmealDTO);
        return Result.success(save.toString());

    }


    /**
     * 套餐设置状态
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐设置状态")
    public Result setStatus(@PathVariable("status") Integer status,
                            Long id){
        log.info("套餐设置状态");
        setmealService.setStatus(status,id);
        return Result.success();
    }
//
//    /**
//     * 修改菜品
//     */
//    @GetMapping("/{id}")
//    public Result<DishVO> getById(@PathVariable("id") Long id){
//        DishVO dishVO = dishService.getById(id);
//        return Result.success(dishVO);
//    }
//
//
//
//    @DeleteMapping
//    @ApiOperation("删除菜品")
//    public Result delete(@RequestParam List<Long> ids){
//        log.info("删除菜品");
//        dishService.deleteBatch(ids);
//        return Result.success();
//    }
//
//
//    /**
//     * 修改菜品信息
//     * @return
//     */
//    @PutMapping
//    @ApiOperation("修改菜品信息")
//    public Result<Dish> update(@RequestBody DishDTO dishDTO){
//        log.info("修改菜品信息");
//        dishService.updateWithFlavor(dishDTO);
//        return Result.success();
//    }
//
//    @GetMapping("/list")
//    @ApiOperation("根据条件查询菜品数据")
//    public Result<List<Dish>> listById(@RequestParam Integer categoryId){
//        List<Dish> list = dishService.listById(categoryId);
//        return Result.success(list);
//    }



}
