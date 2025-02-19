package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.*;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 分类查询
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("员工分页查询：{}",categoryPageQueryDTO);
        PageResult page1 = categoryService.page(categoryPageQueryDTO);
        return Result.success(page1);
    }


    /**
     *新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}",categoryDTO);

        System.out.println("当前线程id" + Thread.currentThread().getId());
        Integer save = categoryService.save(categoryDTO);
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
        categoryService.setStatus(status,id);
        return Result.success();
    }

    /**
     * 修改分类信息
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类信息")
    public Result<Category> getById(@RequestBody CategoryDTO categoryDTO){
        log.info("修改分类信息");
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    public Result delete(Long id){
        log.info("删除分类");
        categoryService.delete(id);
        return Result.success();
    }



    @GetMapping("/list")
    @ApiOperation("查询分类")
    public Result<List<CategoryDTO>> selectList(@RequestParam Integer type){
        log.info("查询分类");
        List<CategoryDTO> categoryDTOS = categoryService.selectList(type);
        return Result.success(categoryDTOS);
    }


}
