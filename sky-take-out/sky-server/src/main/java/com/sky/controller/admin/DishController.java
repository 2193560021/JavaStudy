package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 菜品分页查询
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询：{}",dishPageQueryDTO);
        PageResult page1 = dishService.page(dishPageQueryDTO);
        return Result.success(page1);
    }

    /**
     *新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result<String> save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);

        System.out.println("当前线程id" + Thread.currentThread().getId());
        Integer save = dishService.saveWithFlavor(dishDTO);

        String key = "dish:" + dishDTO.getCategoryId();

        redisTemplate.delete(key);
        return Result.success(save.toString());

    }


    /**
     * 菜品设置状态
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品设置状态")
    public Result setStatus(@PathVariable("status") Integer status,
                            Long id){
        log.info("菜品设置状态");
        dishService.setStatus(status,id);

        cleanCache("dish:*");
        return Result.success();
    }

    /**
     * 修改菜品
     */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable("id") Long id){
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }



    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品");
        dishService.deleteBatch(ids);


        cleanCache("dish:*");

        return Result.success();
    }


    /**
     * 修改菜品信息
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品信息")
    public Result<Dish> update(@RequestBody DishDTO dishDTO){
        log.info("修改菜品信息");
        dishService.updateWithFlavor(dishDTO);

        cleanCache("dish:*");

        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据条件查询菜品数据")
    public Result<List<Dish>> listById(@RequestParam Integer categoryId){
        List<Dish> list = dishService.listById(categoryId);
        return Result.success(list);
    }

    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }


}
