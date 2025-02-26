package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.service.SetmealService;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
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
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result setStatus(@PathVariable("status") Integer status,
                            Long id){
        log.info("套餐设置状态");
        setmealService.setStatus(status,id);
        return Result.success();
    }

    /**
     * 修改菜品
     */
    @GetMapping("/{id}")
    public Result<SetmealVO> getById(@PathVariable("id") Long id){
        SetmealVO setmealVO = setmealService.getById(id);
        return Result.success(setmealVO);
    }



    @DeleteMapping
    @ApiOperation("删除菜品")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品");
        setmealService.deleteBatch(ids);
        return Result.success();
    }


    /**
     * 修改套餐信息
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐信息")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result<Setmeal> update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐信息:{}",setmealDTO);
        setmealService.updateWithFlavor(setmealDTO);
        return Result.success();
    }
//
//    @GetMapping("/list")
//    @ApiOperation("根据条件查询菜品数据")
//    public Result<List<Dish>> listById(@RequestParam Integer categoryId){
//        List<Dish> list = dishService.listById(categoryId);
//        return Result.success(list);
//    }



}
