package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "店铺相关接口")
public class ShopController {

    @Autowired
    public ShopService shopService;

    @PutMapping("/{status}")
    @ApiOperation("店铺营业状态修改")
    public Result<String> startOrStop(@PathVariable("status") Integer status){
        log.info("店铺营业状态修改：{}",status == 1 ? "开启营业" : "结束营业");
        shopService.startOrStop(status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询店铺的营业状态")
    public Result<Integer> getStatus(){
        log.info("查询店铺的营业状态");
        Integer status = shopService.getStatus();
        return Result.success(status);
    }

}
